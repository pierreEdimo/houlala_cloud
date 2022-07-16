package com.example.marketplaceclient.services;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.feign.StockServiceFeignClient;
import com.example.marketplaceclient.feign.ProductServiceFeignClient;
import com.example.marketplaceclient.feign.UploadServiceFeignClient;
import com.example.marketplaceclient.model.CreateProduct;
import com.example.marketplaceclient.model.ProductAdditionalInformation;
import com.example.marketplaceclient.model.Product;
import com.example.marketplaceclient.model.dto.CreateProductDto;
import com.example.marketplaceclient.model.dto.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    private final StockServiceFeignClient stockerServiceFeignClient;

    private final UploadServiceFeignClient uploadServiceFeignClient;


    @Override
    public List<ProductDto> getAllProducts() {

        List<ProductDto> productDtoList = new ArrayList<>();
        List<ProductAdditionalInformation> infos;
        List<Product> productResponses;

        try {
            productResponses = this.feignClient.getAllProducts();
            infos = this.stockerServiceFeignClient.getAllProductInfos();
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
    public ProductDto getProduct(String id) {
        Product product;
        ProductAdditionalInformation info;

        try {
            product = this.feignClient.getSingleProduct(id);
            info = this.stockerServiceFeignClient.getASingleInfo(product.get_id());
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        return this.toProductDto(product, info);
    }

    @Override
    public List<ProductDto> searchProduct(String searchWord) {
        List<Product> productResponses;
        List<ProductDto> productDtoList = new ArrayList<>();
        List<ProductAdditionalInformation> infos;

        try {
            productResponses = this.feignClient.searchProduct(searchWord);
            infos = this.stockerServiceFeignClient.getAllProductInfos();
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
    public List<ProductDto> getRandomProducts(int size, String categoryId) {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Product> productList;
        List<ProductAdditionalInformation> infos;

        try {
            productList = this.feignClient.getRandomProducts(size, categoryId);
            infos = this.stockerServiceFeignClient.getAllProductInfos();
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        for (Product response : productList) {
            for (ProductAdditionalInformation info : infos) {
                if (response.getProductSku().equals(info.getProductSku())) {
                    productDtoList.add(this.toProductDto(response, info));
                }
            }
        }

        return productDtoList;
    }

    @Override
    public List<ProductDto> getProductsByCategoryId(String categoryId, int limit) {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Product> productList;
        List<ProductAdditionalInformation> infos;

        try {
            productList = this.feignClient.getProductByCategoryId(categoryId, limit);
            infos = this.stockerServiceFeignClient.getAllProductInfos();
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        for (Product response : productList) {
            for (ProductAdditionalInformation info : infos) {
                if (response.getProductSku().equalsIgnoreCase(info.getProductSku())) {
                    productDtoList.add(this.toProductDto(response, info));
                }
            }
        }

        return productDtoList;
    }

    @Override
    public List<ProductDto> getProductsByTypeAndCategoryId(String categoryId, String productType) {

        List<Product> productList;
        List<ProductDto> productDtoList = new ArrayList<>();
        List<ProductAdditionalInformation> infos;

        try {
            productList = this.feignClient.getProductsByTypeAndCategoryId(categoryId, productType);
            infos = this.stockerServiceFeignClient.getAllProductInfos();
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        for (Product response : productList) {
            for (ProductAdditionalInformation info : infos) {
                if (response.getProductSku().equalsIgnoreCase(info.getProductSku())) {
                    productDtoList.add(this.toProductDto(response, info));
                }
            }
        }

        return productDtoList;
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
    public List<ProductDto> getProductByLocationId(String locationId, int limit) {
        List<Product> productList;
        List<ProductAdditionalInformation> infos;
        List<ProductDto> productDtoList = new ArrayList<>();

        try {
            productList = this.feignClient.getProductByLocationId(locationId, limit);
            infos = this.stockerServiceFeignClient.getAllProductInfos();
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        for (Product product : productList) {
            for (ProductAdditionalInformation info : infos) {
                if (product.getProductSku().equalsIgnoreCase(info.getProductSku())) {
                    productDtoList.add(this.toProductDto(product, info));
                }
            }
        }

        return productDtoList;
    }

    @Override
    public ProductDto getProductByIdAndIsFavorite(String id, String userId) {
        Product response;
        ProductAdditionalInformation additionalInformation;

        try {
            response = this.feignClient.getProductByIdAndIsFavorite(id, userId);
            additionalInformation = this.stockerServiceFeignClient.getASingleInfo(response.get_id());

        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        return this.toProductDto(response, additionalInformation);
    }

    @Override
    public List<ProductDto> getProductsWithLimit(int limit) {

        List<ProductDto> productDtoList = new ArrayList<>();
        List<Product> productList;
        List<ProductAdditionalInformation> informations;

        try {
            productList = this.feignClient.getProductsWithLimit(limit);
            informations = this.stockerServiceFeignClient.getAllProductInfos();
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        for (Product product : productList) {
            for (ProductAdditionalInformation info : informations) {
                productDtoList.add(this.toProductDto(product, info));
            }
        }

        return productDtoList;
    }

    @Override
    public String createProduct(String createProduct, MultipartFile file) {

        CreateProductDto newProduct;
        String imageUrl;


        try {
            ObjectMapper objectMapper = new ObjectMapper();
            newProduct = objectMapper.readValue(createProduct, CreateProductDto.class);
        } catch (IOException io) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, io.getMessage());
        }

        String productSku = this.skuGenerator(newProduct.getName(), newProduct.getOriginLabel(), newProduct.getLocationId());

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
                newProduct.getOriginLabel(),
                newProduct.getLocationId()
        );

        try {

            imageUrl = this.uploadServiceFeignClient.uploadFile(file);
            product.setImageUrl(imageUrl);
            this.feignClient.addProduct(product);
            this.stockerServiceFeignClient.addInfo(info);

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

    private String skuGenerator(String productName,
                                String originLabel,
                                String locationId) {
        String result;
        LocalDateTime date = LocalDateTime.now();
        int year = date.getYear();
        int hour = date.getHour();
        int min = date.getMinute();
        String locationIdFirst3Chars = this.getThreeFirstChars(locationId);
        String productFirst3Chars = this.getThreeFirstChars(productName);
        String originFirst3Chars = this.getThreeFirstChars(originLabel);
        result = locationIdFirst3Chars + "-" + productFirst3Chars + "-" + originFirst3Chars + "-" + year + "-" + hour + "" + min;
        return result;
    }

    private String getThreeFirstChars(String str) {
        return str.substring(0, Math.min(str.length(), 3));
    }


}
