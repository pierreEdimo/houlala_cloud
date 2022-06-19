package com.example.marketplaceclient.services;

import com.example.marketplaceclient.model.Product;
import com.example.marketplaceclient.model.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getAllProducts();

    ProductResponse getProduct(String id);

    List<ProductResponse> searchProduct(String searchWord);

    List<ProductResponse> getRandomProducts(int size, String categoryId);

    List<ProductResponse> getProductsByPageId(String pageId, int limit);

    List<ProductResponse> getProductsByCategoryId(String categoryId, int limit);

    List<ProductResponse> getProductsByTypeAndCategoryId(String categoryId, String productType);

    ProductResponse deleteProduct(String id);

    ProductResponse editProduct(String id, Product newProduct);
}
