package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.ProductType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "http://houlala_marketplace:3000/api/product-types", name = "products-types")
public interface ProductTypeFeignClient {

    @GetMapping("")
    List<ProductType> getAllProductTypes() throws MarketplaceException;

    @GetMapping("/{id}")
    ProductType getSingleProductType(@PathVariable(value = "id") String id) throws MarketplaceException;

    @PostMapping("")
    ProductType addProductType(@RequestBody ProductType newType) throws MarketplaceException;

    @PatchMapping("/{id}")
    ProductType editProductType(@RequestBody ProductType newType, @PathVariable(value = "id") String id) throws MarketplaceException;

    @DeleteMapping("/{id}")
    ProductType deleteProductType(@PathVariable(value = "id") String id) throws MarketplaceException;

    @GetMapping("/category/{id}")
    List<ProductType> getTypesByCategoryId(@PathVariable("id") String categoryId) throws MarketplaceException;
}
