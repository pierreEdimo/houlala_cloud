package com.example.marketplaceclient.services;

import com.example.marketplaceclient.model.CreateProduct;
import com.example.marketplaceclient.model.Product;
import com.example.marketplaceclient.model.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();

    ProductDto getProduct(String id);

    List<ProductDto> searchProduct(String searchWord);

    Product deleteProduct(String id);

    Product editProduct(String id, CreateProduct newProduct);

    void addProductToFavorite(String userId, String id);

    List<Product> getFavoritesProduct(String userId);

    List<ProductDto> getProductByLocationId(String locationId, int limit);

    ProductDto getProductByIdAndIsFavorite(String id, String userId);

    List<ProductDto> getProductsWithLimit(int limit);

    String createProduct(String product, MultipartFile image);

    ProductDto getProductByNameAndIsFavorite(String name, String userId);

    List<ProductDto> getRandomProductsByLocationId(String locationId, int size);

    List<ProductDto> getProductsByType(String typeId, int limit);

    List<ProductDto> getRandomProductsByCategoryId(String categoryId, int size);

    long productTotalCount(String locationId);
}
