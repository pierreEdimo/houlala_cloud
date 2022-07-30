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

    @DeleteMapping("/{id}")
    Product deleteProduct(@PathVariable String id) throws MarketplaceException;

    @PutMapping("/{id}")
    Product editProduct(@PathVariable String id, @RequestBody CreateProduct editProduct) throws MarketplaceException;

    @PostMapping("/favorites/{id}")
    void addProductToFavorite(@RequestParam String userId, @PathVariable String id) throws MarketplaceException;

    @GetMapping("/favorites")
    List<Product> getFavoritesProduct(@RequestParam String userId) throws MarketplaceException;

    @GetMapping("/location/{id}")
    List<Product> getProductByLocationId(@PathVariable("id") String locationId, @RequestParam int limit) throws MarketplaceException;

    @GetMapping("/product/{id}")
    Product getProductByIdAndIsFavorite(@PathVariable String id, @RequestParam String userId) throws MarketplaceException;

    @GetMapping("/limit/{limit}")
    List<Product> getProductsWithLimit(@PathVariable int limit) throws MarketplaceException;

    @PostMapping("")
    Product addProduct(@RequestBody CreateProduct newProduct) throws MarketplaceException;

    @GetMapping("/sku/{sku}")
    Product getProductBySku(@PathVariable(name = "sku") String productSku) throws MarketplaceException;

    @GetMapping("/name/{name}")
    Product getProductByNameAndIsFavorite(@PathVariable("name") String name, @RequestParam String userId) throws MarketplaceException;

    @GetMapping("random/location/{id}")
    List<Product> getRandomProductsByLocationId(@PathVariable("id") String locationId, @RequestParam int size) throws MarketplaceException;

    @GetMapping("type/{id}")
    List<Product> getProductsByType(@PathVariable("id") String typeId, @RequestParam int limit) throws MarketplaceException;

    @GetMapping("/random/category/{id}")
    List<Product> getProductsByCategoryId(@PathVariable("id") String categoryId, @RequestParam int size) throws MarketplaceException;


}
