package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.SubCategory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "http://houlala_marketplace:3000/api/product-types", name = "products-types")
public interface ProductTypeFeignClient {

    @GetMapping("")
    List<SubCategory> getAllProductTypes() throws MarketplaceException;

    @GetMapping("/{id}")
    SubCategory getSingleProductType(@PathVariable(value = "id") String id) throws MarketplaceException;

    @PostMapping("")
    SubCategory addProductType(@RequestBody SubCategory newType) throws MarketplaceException;

    @PatchMapping("/{id}")
    SubCategory editProductType(@RequestBody SubCategory newType, @PathVariable(value = "id") String id) throws MarketplaceException;

    @DeleteMapping("/{id}")
    SubCategory deleteProductType(@PathVariable(value = "id") String id) throws MarketplaceException;

    @GetMapping("/category/{id}")
    List<SubCategory> getTypesByCategoryId(@PathVariable("id") String categoryId) throws MarketplaceException;
}
