package com.example.marketplaceclient.controller;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.feign.CategoryServiceFeignClient;
import com.example.marketplaceclient.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Categories")
public class CategoryController {

    private final CategoryServiceFeignClient feignClient;

    @GetMapping("")
    public List<Category> getAllCategories() {
        try {
            return this.feignClient.getAllCategories();
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PostMapping("")
    public Category addCategory(@RequestBody Category newCategory) {
        try {
            return this.feignClient.addCategory(newCategory);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/filterCategoriesWithLimit")
    public List<Category> getCategoriesWithLimit(@RequestParam int limit) {
        try {
            return this.feignClient.getCategoriesWithLimit(limit);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/search")
    public List<Category> searchCategories(@RequestParam(name = "searchword") String search) {
        try {
            return this.feignClient.searchCategories(search);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/{id}")
    Category getSingleCategory(@PathVariable String id) {
        try {
            return this.feignClient.getSingleCategory(id);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    Category editCategory(@PathVariable String id, @RequestBody Category newCategory) {
        try {
            return this.feignClient.editCategory(id, newCategory);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    Category deleteCategory(@PathVariable String id){
        try {
            return this.feignClient.deleteCategory(id);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }
}
