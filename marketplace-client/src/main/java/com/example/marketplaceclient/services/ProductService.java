package com.example.marketplaceclient.services;

import com.example.marketplaceclient.model.Product;
import com.example.marketplaceclient.model.ProductResponse;
import com.example.marketplaceclient.model.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();

    ProductResponse getProduct(String id);

    List<ProductResponse> searchProduct(String searchWord);

    List<ProductResponse> getRandomProducts(int size, String categoryId);

    List<ProductResponse> getProductsByCategoryId(String categoryId, int limit);

    List<ProductResponse> getProductsByTypeAndCategoryId(String categoryId, String productType);

    ProductResponse deleteProduct(String id);

    ProductResponse editProduct(String id, Product newProduct);

    void addProductToFavorite(String userId, String id);

    List<ProductResponse> getFavoritesProduct(String userId);

    List<ProductResponse> getProductByLocationId(String locationId, int limit);

    ProductDto getProductByIdAndIsFavorite(String id, String userId);

    List<ProductResponse> getProductsWithLimit(int limit);
}
