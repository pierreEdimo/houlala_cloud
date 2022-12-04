package com.example.marketplaceclient.controller;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.feign.CategoryServiceFeignClient;
import com.example.marketplaceclient.feign.UploadServiceFeignClient;
import com.example.marketplaceclient.model.Category;
import com.example.marketplaceclient.model.dto.CreateCategoryDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryServiceFeignClient feignClient;

    private final UploadServiceFeignClient uploadServiceFeignClient;

    @GetMapping
    public List<Category> getAllCategories() {
        try {
            return this.feignClient.getAllCategories();
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PostMapping
    public Category addCategory(@RequestPart(value = "category") String category, @RequestPart(value = "image") MultipartFile image) {
        Category createdCategory;
        CreateCategoryDto newCategory;
        String imageUrl;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            newCategory = objectMapper.readValue(category, CreateCategoryDto.class);
        } catch (IOException io) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, io.getMessage());
        }

        createdCategory = new Category(
                newCategory.getName(),
                newCategory.getDescription()
        );

        try {
            imageUrl = this.uploadServiceFeignClient.uploadThumbnailImage(image);
            createdCategory.setImageUrl(imageUrl);
            return this.feignClient.addCategory(createdCategory);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

    }

    @GetMapping("/filter")
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
    Category deleteCategory(@PathVariable String id) {
        try {
            return this.feignClient.deleteCategory(id);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/random")
    public List<Category> getRandomCategories(@RequestParam int size) {
        try {
            return this.feignClient.getRandomCategories(size);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }
}
