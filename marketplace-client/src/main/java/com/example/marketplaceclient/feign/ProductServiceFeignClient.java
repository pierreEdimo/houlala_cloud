package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.CreateProduct;
import com.example.marketplaceclient.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "http://houlala_marketplace:3000/api/products", name = "products")
public interface ProductServiceFeignClient {

    @GetMapping("")
    List<Product> getAllProducts() throws MarketplaceException;

    @GetMapping("/{id}")
    Product getSingleProduct(@PathVariable(value = "id") String id) throws MarketplaceException;

    @GetMapping("/search")
    List<Product> searchProduct(@RequestParam(value = "searchword") String searchWord) throws MarketplaceException;

    @DeleteMapping("/{id}")
    Product deleteProduct(@PathVariable(value = "id") String id) throws MarketplaceException;

    @PutMapping("/{id}")
    Product editProduct(@PathVariable(value = "id") String id, @RequestBody CreateProduct editProduct) throws MarketplaceException;

    @PostMapping("/favorites/{id}")
    void addProductToFavorite(@RequestParam(value = "userId") String userId, @PathVariable(value = "id") String id) throws MarketplaceException;

    @GetMapping("/favorites")
    List<Product> getFavoritesProduct(@RequestParam(value = "userId") String userId) throws MarketplaceException;

    @GetMapping("/location/{id}")
    List<Product> getProductByLocationId(@PathVariable("id") String locationId, @RequestParam(value = "limit", required = false) int limit) throws MarketplaceException;

    @GetMapping("/product/{id}")
    Product getProductByIdAndIsFavorite(@PathVariable(value = "id") String id, @RequestParam(value = "userId") String userId) throws MarketplaceException;

    @GetMapping("/limit/{limit}")
    List<Product> getProductsWithLimit(@PathVariable(value = "limit") int limit) throws MarketplaceException;

    @PostMapping("")
    Product addProduct(@RequestBody CreateProduct newProduct) throws MarketplaceException;

    @GetMapping("/sku/{sku}")
    Product getProductBySku(@PathVariable(name = "sku") String productSku) throws MarketplaceException;

    @GetMapping("/name/{name}")
    Product getProductByNameAndIsFavorite(@PathVariable("name") String name, @RequestParam(value = "userId") String userId) throws MarketplaceException;

    @GetMapping("/favorite/sku/{sku}")
    Product getProductBySkuAndIsFavorite(@PathVariable("sku") String productSku, @RequestParam(value = "userId") String userId) throws MarketplaceException;

    @GetMapping("random/location/{id}")
    List<Product> getRandomProductsByLocationId(@PathVariable("id") String locationId, @RequestParam(value = "size") int size) throws MarketplaceException;

    @GetMapping("type/{id}")
    List<Product> getProductsByType(@PathVariable("id") String typeId, @RequestParam(value = "limit") int limit) throws MarketplaceException;

    @GetMapping("/random/category/{id}")
    List<Product> getRandomProductsByCategoryId(@PathVariable("id") String categoryId, @RequestParam(value = "size") int size) throws MarketplaceException;

    @GetMapping("/category/{id}")
    List<Product> getProductsByCategoryId(@PathVariable("id") String categoryId, @RequestParam(value = "size", required = false) int size) throws MarketplaceException;


}
