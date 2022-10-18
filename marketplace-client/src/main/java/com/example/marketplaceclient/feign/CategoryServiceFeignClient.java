package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "http://houlala_marketplace:3000/api/categories", name = "categories")
public interface CategoryServiceFeignClient {

    @GetMapping("")
    List<Category> getAllCategories() throws MarketplaceException;

    @GetMapping("/filterCategoriesWithLimit")
    List<Category> getCategoriesWithLimit(@RequestParam int limit) throws MarketplaceException;

    @GetMapping("/search")
    List<Category> searchCategories(@RequestParam(name = "searchword") String search) throws MarketplaceException;

    @GetMapping("/{id}")
    Category getSingleCategory(@PathVariable String id) throws MarketplaceException;

    @PostMapping("")
    Category addCategory(@RequestBody Category newCategory) throws MarketplaceException;

    @PatchMapping("/{id}")
    Category editCategory(@PathVariable String id, @RequestBody Category newCategory) throws MarketplaceException;

    @DeleteMapping("/{id}")
    Category deleteCategory(@PathVariable String id) throws MarketplaceException;

    @GetMapping("/random")
    List<Category> getRandomCategories(@RequestParam int size) throws MarketplaceException;
}
