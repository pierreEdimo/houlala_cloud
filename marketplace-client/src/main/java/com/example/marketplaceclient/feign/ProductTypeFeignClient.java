package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.ProductType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "https://houlala.herokuapp.com/api/product-types", name = "products-types")
public interface ProductTypeFeignClient {

    @GetMapping("")
    List<ProductType> getAllProductTypes() throws MarketplaceException;

    @GetMapping("/{id}")
    ProductType getSingleProductType(@PathVariable String id) throws MarketplaceException;

    @PostMapping("")
    ProductType addProductType(@RequestBody ProductType newType) throws MarketplaceException;

    @PatchMapping("/{id}")
    ProductType editProductType(@RequestBody ProductType newType, @PathVariable String id) throws MarketplaceException;

    @DeleteMapping("/{id}")
    ProductType deleteProductType(@PathVariable String id) throws MarketplaceException;
}
