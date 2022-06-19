package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.Product;
import com.example.marketplaceclient.model.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "https://houlala.herokuapp.com/api/products", name = "products")
public interface ProductServiceFeignClient {

    @GetMapping("")
    List<ProductResponse> getAllProducts() throws MarketplaceException;

    @GetMapping("/{id}")
    ProductResponse getSingleProduct(@PathVariable String id) throws MarketplaceException;

    @GetMapping("/search")
    List<ProductResponse> searchProduct(@RequestParam String searchWord) throws MarketplaceException;

    @GetMapping("/getRandomProducts")
    List<ProductResponse> getRandomProducts(@RequestParam int size, @RequestParam String categoryId) throws MarketplaceException;

    @GetMapping("/filterProductByPageId")
    List<ProductResponse> getProductByPageId(@RequestParam String pageId, @RequestParam int limit) throws MarketplaceException;

    @GetMapping("/filterProductsByCategoryId")
    List<ProductResponse> getProductByCategoryId(@RequestParam String categoryId, @RequestParam int limit) throws MarketplaceException;

    @GetMapping("/filterProductsByCategoryAndProductType")
    List<ProductResponse> getProductsByTypeAndCategoryId(@RequestParam String categoryId, @RequestParam String productType) throws MarketplaceException;

    @DeleteMapping("/{id}")
    ProductResponse deleteProduct(@PathVariable String id) throws MarketplaceException;

    @PatchMapping("/{id}")
    ProductResponse editProduct(@PathVariable String id, @RequestBody Product editProduct) throws MarketplaceException;

}
