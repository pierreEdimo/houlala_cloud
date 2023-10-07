package com.houlala.marketplace.product;
import com.houlala.marketplace.model.Count;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();

    void createProduct(String product, MultipartFile imageUrl);

    ProductDto getProductBySkuAndIsFavorite(String sku, String userId);

    ProductDto getProductBySku(String sku);

    List<ProductDto> getProductsByLocationId(String id, int size);

    List<ProductDto> getProductsByCategoryId(long id, int size);

    List<ProductDto> getProductsFavoritesList(String userId);

    void addProductToFavorites(int id, String userId);

    List<ProductDto> getProductsBySubCategoryId(int id, int size);

    List<ProductDto> getRandomProductsByLocationId(String id, int size);

    List<ProductDto> getRandomProductsByCategoryId(int id, int size);

    List<ProductDto> getFilteredProducts(String word);

    void deleteProduct(int id);

    void editProduct(int id, Product product);

    List<ProductDto> getProductsWithLimit(int size);

    List<ProductDto> getTopSellingProductsByLocationId(String luid);

    List<ProductDto> getProductSoonOutOfStock(String luid);

    int getProductsTotalCount(String luid);

}