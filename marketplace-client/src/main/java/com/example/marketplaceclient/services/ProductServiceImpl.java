package com.example.marketplaceclient.services;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.feign.InventoryServiceFeignClient;
import com.example.marketplaceclient.feign.ProductServiceFeignClient;
import com.example.marketplaceclient.feign.UploadServiceFeignClient;
import com.example.marketplaceclient.model.CreateProduct;
import com.example.marketplaceclient.model.ProductAdditionalInformation;
import com.example.marketplaceclient.model.Product;
import com.example.marketplaceclient.model.dto.CreateProductDto;
import com.example.marketplaceclient.model.dto.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductServiceFeignClient feignClient;

    private final InventoryServiceFeignClient inventoryServiceFeignClient;

    private final UploadServiceFeignClient uploadServiceFeignClient;

    @Override
    public List<ProductDto> getAllProducts() {

        List<ProductDto> productDtoList = new ArrayList<>();
        List<ProductAdditionalInformation> infos;
        List<Product> productResponses;

        try {
            productResponses = this.feignClient.getAllProducts();
            infos = this.inventoryServiceFeignClient.getAllProductInfos();
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        for (Product response : productResponses) {
            for (ProductAdditionalInformation info : infos) {
                if (response.getProductSku().equals(info.getProductSku())) {
                    productDtoList.add(this.toProductDto(response, info));
                }
            }
        }

        return productDtoList;
    }

    @Override
    public Product getProduct(String id) {
        try {
            return this.feignClient.getSingleProduct(id);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public List<Product> searchProduct(String searchWord) {
        try {
            return this.feignClient.searchProduct(searchWord);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public List<Product> getRandomProducts(int size, String categoryId) {
        try {
            return this.feignClient.getRandomProducts(size, categoryId);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public List<Product> getProductsByCategoryId(String categoryId, int limit) {
        try {
            return this.feignClient.getProductByCategoryId(categoryId, limit);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public List<Product> getProductsByTypeAndCategoryId(String categoryId, String productType) {
        try {
            return this.feignClient.getProductsByTypeAndCategoryId(categoryId, productType);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public Product deleteProduct(String id) {
        try {
            return this.feignClient.deleteProduct(id);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public Product editProduct(String id, CreateProduct newProduct) {
        try {
            return this.feignClient.editProduct(id, newProduct);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public void addProductToFavorite(String userId, String id) {
        try {
            this.feignClient.addProductToFavorite(userId, id);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public List<Product> getFavoritesProduct(String userId) {
        try {
            return this.feignClient.getFavoritesProduct(userId);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public List<Product> getProductByLocationId(String locationId, int limit) {
        try {
            return this.feignClient.getProductByLocationId(locationId, limit);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public ProductDto getProductByIdAndIsFavorite(String id, String userId) {
        Product response;
        ProductAdditionalInformation additionalInformation;

        try {
            response = this.feignClient.getProductByIdAndIsFavorite(id, userId);
            additionalInformation = this.inventoryServiceFeignClient.getASingleInfo(response.get_id());

        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        return this.toProductDto(response, additionalInformation);
    }

    @Override
    public List<Product> getProductsWithLimit(int limit) {
        try {
            return this.feignClient.getProductsWithLimit(limit);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public String createProduct(String createProduct, MultipartFile file) {

        CreateProductDto newProduct = new CreateProductDto();
        String imageUrl;


        try {
            ObjectMapper objectMapper = new ObjectMapper();
            newProduct = objectMapper.readValue(createProduct, CreateProductDto.class);
        } catch (IOException io) {
            System.out.println("Error");
        }

        String productSku = this.skuGenerator(newProduct.getName(), newProduct.getOriginLabel());

        CreateProduct product = new CreateProduct(
                newProduct.getName(),
                newProduct.getDescription(),
                newProduct.getWeight(),
                newProduct.getSellingPrice(),
                newProduct.getLocationId(),
                productSku.toLowerCase(),
                newProduct.getCategoryId(),
                newProduct.getProductType()
        );

        ProductAdditionalInformation info = new ProductAdditionalInformation(
                productSku.toLowerCase(),
                newProduct.getQuantity(),
                newProduct.getBuyingPrice(),
                newProduct.getOriginLabel()
        );

        try {

            imageUrl = this.uploadServiceFeignClient.uploadFile(file);
            product.setImageUrl(imageUrl);
            this.feignClient.addProduct(product);
            this.inventoryServiceFeignClient.addInfo(info);

        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        return "createProduct has been succesfully created";
    }

    private ProductDto toProductDto(Product response, ProductAdditionalInformation additionalInformation) {
        return new ProductDto(
                response.get_id(),
                response.getName(),
                response.getDescription(),
                response.getWeight(),
                response.getImageUrl(),
                response.getSellingPrice(),
                response.getLocationId(),
                response.isBookMarked(),
                additionalInformation.getQuantity(),
                additionalInformation.getArrivalDate(),
                additionalInformation.getBuyingPrice(),
                additionalInformation.getOriginLabel(),
                additionalInformation.getProductSku()
        );
    }

    private String skuGenerator(String productName, String originLabel) {
        String result;
        LocalDateTime date = LocalDateTime.now();
        int year = date.getYear();
        int hour = date.getHour();
        int min = date.getMinute();
        String productFirst3Chars = productName.substring(0, Math.min(productName.length(), 3));
        String originFirst3Chars = originLabel.substring(0, Math.min(productName.length(), 3));
        result = productFirst3Chars + "-" + originFirst3Chars + "-" + year + "-" + hour + "" + min;
        return result;
    }


}
