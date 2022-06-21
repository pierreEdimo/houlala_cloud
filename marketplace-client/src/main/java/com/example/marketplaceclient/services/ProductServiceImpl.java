package com.example.marketplaceclient.services;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.feign.InventoryServiceFeignClient;
import com.example.marketplaceclient.feign.ProductServiceFeignClient;
import com.example.marketplaceclient.model.Product;
import com.example.marketplaceclient.model.ProductAdditionalInformation;
import com.example.marketplaceclient.model.ProductResponse;
import com.example.marketplaceclient.model.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductServiceFeignClient feignClient;

    private final InventoryServiceFeignClient inventoryServiceFeignClient;

    @Override
    public List<ProductResponse> getAllProducts() {
        try {
            return this.feignClient.getAllProducts();
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
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
        ProductResponse response = new ProductResponse();
        try {
            response =  this.feignClient.getProductByIdAndIsFavorite(id, userId);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        return this.toProductDto(response);
    }

    @Override
    public List<ProductResponse> getProductsWithLimit(int limit) {
        try {
            return this.feignClient.getProductsWithLimit(limit);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }


    private ProductDto toProductDto(ProductResponse response){
        ProductAdditionalInformation additionalInformation = new ProductAdditionalInformation();
        try {
            additionalInformation = this.inventoryServiceFeignClient.getASingleInfo(response.get_id());
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

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
                additionalInformation.getOriginLabel()
        );
    }


}
