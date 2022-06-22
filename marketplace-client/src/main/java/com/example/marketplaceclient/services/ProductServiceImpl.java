package com.example.marketplaceclient.services;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.feign.InventoryServiceFeignClient;
import com.example.marketplaceclient.feign.ProductServiceFeignClient;
import com.example.marketplaceclient.model.CreateProduct;
import com.example.marketplaceclient.model.Product;
import com.example.marketplaceclient.model.ProductAdditionalInformation;
import com.example.marketplaceclient.model.ProductResponse;
import com.example.marketplaceclient.model.dto.CreateProductDto;
import com.example.marketplaceclient.model.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductServiceFeignClient feignClient;

    private final InventoryServiceFeignClient inventoryServiceFeignClient;

    @Override
    public List<ProductDto> getAllProducts() {

        List<ProductDto> productDtoList = new ArrayList<>();
        List<ProductAdditionalInformation> infos;
        List<ProductResponse> productResponses;

        try {
            productResponses = this.feignClient.getAllProducts();
            infos = this.inventoryServiceFeignClient.getAllProductInfos();
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        for (ProductResponse response : productResponses) {
            for (ProductAdditionalInformation info : infos) {
                if (response.getProductSku().equals(info.getProductSku())) {
                    productDtoList.add(this.toProductDto(response, info));
                }
            }
        }

        return productDtoList;
    }

    @Override
    public ProductResponse getProduct(String id) {
        try {
            return this.feignClient.getSingleProduct(id);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public List<ProductResponse> searchProduct(String searchWord) {
        try {
            return this.feignClient.searchProduct(searchWord);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public List<ProductResponse> getRandomProducts(int size, String categoryId) {
        try {
            return this.feignClient.getRandomProducts(size, categoryId);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public List<ProductResponse> getProductsByCategoryId(String categoryId, int limit) {
        try {
            return this.feignClient.getProductByCategoryId(categoryId, limit);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public List<ProductResponse> getProductsByTypeAndCategoryId(String categoryId, String productType) {
        try {
            return this.feignClient.getProductsByTypeAndCategoryId(categoryId, productType);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public ProductResponse deleteProduct(String id) {
        try {
            return this.feignClient.deleteProduct(id);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public ProductResponse editProduct(String id, Product newProduct) {
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
    public List<ProductResponse> getFavoritesProduct(String userId) {
        try {
            return this.feignClient.getFavoritesProduct(userId);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public List<ProductResponse> getProductByLocationId(String locationId, int limit) {
        try {
            return this.feignClient.getProductByLocationId(locationId, limit);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public ProductDto getProductByIdAndIsFavorite(String id, String userId) {
        ProductResponse response;
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
    public List<ProductResponse> getProductsWithLimit(int limit) {
        try {
            return this.feignClient.getProductsWithLimit(limit);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public ProductResponse addProduct(CreateProductDto newProduct) {

        CreateProduct product = new CreateProduct(
                newProduct.getName(),
                newProduct.getDescription(),
                newProduct.getWeight(),
                newProduct.getSellingPrice(),
                newProduct.getLocationId(),
                newProduct.getProductSku(),
                newProduct.getCategoryId(),
                newProduct.getProductType()
        );

        ProductAdditionalInformation info = new ProductAdditionalInformation(
                newProduct.getProductSku(),
                newProduct.getQuantity(),
                newProduct.getBuyingPrice(),
                newProduct.getOriginLabel()
        );

        try {
            this.feignClient.addProduct(product);
            this.inventoryServiceFeignClient.addInfo(info);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        return new ProductResponse(
                newProduct.getName(),
                newProduct.getDescription(),
                newProduct.getWeight(),
                newProduct.getSellingPrice(),
                newProduct.getLocationId(),
                newProduct.getProductSku()
        );
    }


    private ProductDto toProductDto(ProductResponse response, ProductAdditionalInformation additionalInformation) {
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


}
