package com.example.marketplaceclient.services;

import com.example.marketplaceclient.model.CreateProduct;
import com.example.marketplaceclient.model.Product;
import com.example.marketplaceclient.model.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();

    Product getProduct(String id);

    List<Product> searchProduct(String searchWord);

    List<Product> getRandomProducts(int size, String categoryId);

    List<Product> getProductsByCategoryId(String categoryId, int limit);

    List<Product> getProductsByTypeAndCategoryId(String categoryId, String productType);

    Product deleteProduct(String id);

    Product editProduct(String id, CreateProduct newProduct);

    void addProductToFavorite(String userId, String id);

    List<Product> getFavoritesProduct(String userId);

    List<Product> getProductByLocationId(String locationId, int limit);

    ProductDto getProductByIdAndIsFavorite(String id, String userId);

    List<Product> getProductsWithLimit(int limit);

    String createProduct(String product, MultipartFile file);
}
