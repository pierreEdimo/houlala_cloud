package com.houlala.marketplace.subCategories;

import com.houlala.marketplace.exception.MarketplaceException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "sub-categories", url = "https://marketplace-api.houlala.store/api/sub-categories")
public interface SubCategoryFeign {
    @GetMapping("/{id}")
    SubCategory getSingleCategory(@PathVariable long id) throws MarketplaceException;

    @PostMapping
    SubCategory createSubCategory(@RequestBody CreateSubCategory category) throws MarketplaceException;

    @GetMapping("/categories/{id}")
    List<SubCategory> getCategoriesByParentCategory(@PathVariable long id) throws MarketplaceException;

    @GetMapping
    List<SubCategory> getAllCategories() throws MarketplaceException;

    @DeleteMapping("/{id}")
    void deleteCategory(@PathVariable long id) throws MarketplaceException;

    @PutMapping("/{id}")
    void editCategory(@PathVariable long id, @RequestBody SubCategory subCategory);
}
