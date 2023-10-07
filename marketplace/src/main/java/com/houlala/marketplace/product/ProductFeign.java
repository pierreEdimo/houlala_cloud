package com.houlala.marketplace.product;

import com.houlala.marketplace.exception.MarketplaceException;
import com.houlala.marketplace.model.Count;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product", url = "https://marketplace-api.houlala.store/api/products")
public interface ProductFeign {
    @GetMapping
    List<Product> getAllProducts() throws MarketplaceException;

    @PostMapping
    void createProduct(@RequestBody CreateProduct product) throws MarketplaceException;

    @GetMapping("{sku}/users/{userId}")
    Product getProductBySkuAndIsFavorite(@PathVariable String sku, @PathVariable String userId) throws MarketplaceException;

    @GetMapping("{sku}")
    Product getProductBySku(@PathVariable String sku) throws MarketplaceException;

    @GetMapping("locations/{id}")
    List<Product> getProductsByLocationId(@PathVariable String id, @RequestParam int size ) throws MarketplaceException;

    @GetMapping("categories/{id}")
    List<Product> getProductsByCategoryId(@PathVariable long id, @RequestParam int size) throws MarketplaceException;

    @GetMapping("count/locations/{id}")
    Count getProductsCountByLocationId(@PathVariable String id) throws MarketplaceException;

    @GetMapping("favorites/{userId}")
    List<Product> getProductsFavoritesList(@PathVariable String userId) throws MarketplaceException;

    @PostMapping("favorites/{id}/users/{userId}")
    void addProductToFavorites(@PathVariable int id, @PathVariable String userId) throws MarketplaceException;

    @GetMapping("subCategories/{id}")
    List<Product> getProductsBySubCategoryId(@PathVariable int id, @RequestParam int size) throws MarketplaceException;

    @GetMapping("random/locations/{id}")
    List<Product> getRandomProductsByLocationId(@PathVariable String id, @RequestParam int size) throws MarketplaceException;

    @GetMapping("random/categories/{id}")
    List<Product> getRandomProductsByCategoryId(@PathVariable int id, @RequestParam int size) throws MarketplaceException;

    @GetMapping("filter/search/{word}")
    List<Product> getFilteredProducts(@PathVariable String word) throws MarketplaceException;

    @DeleteMapping("{id}")
    void deleteProduct(@PathVariable int id) throws MarketplaceException;

    @PutMapping("{id}")
    void editProduct(@PathVariable int id, @RequestBody Product product) throws MarketplaceException;

    @GetMapping("limit/{size}")
    List<Product> getProductsWithLimit(@PathVariable int size) throws MarketplaceException;

}
