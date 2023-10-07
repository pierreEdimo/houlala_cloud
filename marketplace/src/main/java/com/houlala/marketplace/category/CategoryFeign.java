package com.houlala.marketplace.category;

import com.houlala.marketplace.exception.MarketplaceException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "categories", url = "https://marketplace-api.houlala.store/api/categories")
public interface CategoryFeign {
    @GetMapping
    List<Category> getAllCategories() throws MarketplaceException;

    @GetMapping("/random/{size}")
    List<Category> getRandomCategories(@PathVariable int size) throws MarketplaceException;

    @GetMapping("/limit/{size}")
    List<Category> getCategoriesWithLimit(@PathVariable int size) throws MarketplaceException;

    @GetMapping("/search")
    List<Category> getFilteredCategories(@RequestParam String word) throws MarketplaceException;

    @GetMapping("/{id}")
    Category getSingleCategory(@PathVariable long id) throws MarketplaceException;

    @PostMapping
    Category createNewCategory(@RequestBody CreateCategory category) throws MarketplaceException;

    @DeleteMapping("/{id}")
    void deleteCategory(@PathVariable long id) throws MarketplaceException;

    @PutMapping("/{id}")
    void editCategory(@PathVariable long id, @RequestBody Category category) throws MarketplaceException;
}
