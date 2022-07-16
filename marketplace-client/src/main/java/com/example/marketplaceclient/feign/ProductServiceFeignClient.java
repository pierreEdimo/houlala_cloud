package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.CreateProduct;
import com.example.marketplaceclient.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "https://houlala.herokuapp.com/api/products", name = "products")
public interface ProductServiceFeignClient {

    @GetMapping("")
    List<Product> getAllProducts() throws MarketplaceException;

    @GetMapping("/{id}")
    Product getSingleProduct(@PathVariable String id) throws MarketplaceException;

    @GetMapping("/search")
    List<Product> searchProduct(@RequestParam String searchWord) throws MarketplaceException;

    @GetMapping("/getRandomProducts")
    List<Product> getRandomProducts(@RequestParam int size, @RequestParam String categoryId) throws MarketplaceException;

    @GetMapping("/filterProductsByCategoryId")
    List<Product> getProductByCategoryId(@RequestParam String categoryId, @RequestParam(required = false) int limit) throws MarketplaceException;

    @GetMapping("/filterProductsByCategoryAndProductType")
    List<Product> getProductsByTypeAndCategoryId(@RequestParam String categoryId, @RequestParam String productType) throws MarketplaceException;

    @DeleteMapping("/{id}")
    Product deleteProduct(@PathVariable String id) throws MarketplaceException;

    @PutMapping("/{id}")
    Product editProduct(@PathVariable String id, @RequestBody CreateProduct editProduct) throws MarketplaceException;

    @PutMapping("/addFavorite/{id}")
    void addProductToFavorite(@RequestParam String userId, @PathVariable String id) throws MarketplaceException;

    @GetMapping("/favorites")
    List<Product> getFavoritesProduct(@RequestParam String userId) throws MarketplaceException;

    @GetMapping("/filterProductByPageId")
    List<Product> getProductByLocationId(@RequestParam String locationId, @RequestParam int limit) throws MarketplaceException;

    @GetMapping("/productId/{id}")
    Product getProductByIdAndIsFavorite(@PathVariable String id, @RequestParam String userId) throws MarketplaceException;

    @GetMapping("/filterProductWithLimit")
    List<Product> getProductsWithLimit(@RequestParam int limit) throws MarketplaceException;

    @PostMapping("")
    Product addProduct(@RequestBody CreateProduct newProduct) throws MarketplaceException;

}
