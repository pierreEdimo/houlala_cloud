package com.example.discoveryorchestrator.controller;

import com.example.discoveryorchestrator.exception.OrchestratorException;
import com.example.discoveryorchestrator.feign.CategoryServiceFeignClient;
import com.example.discoveryorchestrator.feign.UploadServiceFeignClient;
import com.example.discoveryorchestrator.model.Category;
import com.example.discoveryorchestrator.model.dto.CreateCategoryDto;
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
public class OrchestratorCategoryController {

    private final CategoryServiceFeignClient feignClient;

    private final UploadServiceFeignClient uploadServiceFeignClient;

    @PostMapping("")
    public Category createCategory(@RequestPart String newCategory, @RequestPart MultipartFile image) {

        Category category = this.createCategory(newCategory);
        String imageUrl;
        try {
            imageUrl = this.uploadServiceFeignClient.uploadImage(image);
            category.setThumbNail(imageUrl);
            return this.feignClient.createCategory(category);
        } catch (OrchestratorException ex) {
            throw new ResponseStatusException(ex.getHttpStatus(), ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable long id) {
        try {
            this.feignClient.deleteCategory(id);
        } catch (OrchestratorException ex) {
            throw new ResponseStatusException(ex.getHttpStatus(), ex.getMessage());
        }
    }

    @GetMapping("")
    public List<Category> getAllCategories() {
        try {
            return this.feignClient.getAllCategories();
        } catch (OrchestratorException ex) {
            throw new ResponseStatusException(ex.getHttpStatus(), ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Category editCategory(@RequestBody Category newCategory, @PathVariable long id) {
        try {
            return this.feignClient.editCategory(newCategory, id);
        } catch (OrchestratorException ex) {
            throw new ResponseStatusException(ex.getHttpStatus(), ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Category getSingleCategory(@PathVariable long id) {
        try {
            return this.feignClient.getCategory(id);
        } catch (OrchestratorException ex) {
            throw new ResponseStatusException(ex.getHttpStatus(), ex.getMessage());
        }
    }

    @GetMapping("/store")
    public List<Category> getCategoriesStore() {
        try {
            return this.feignClient.getCategoriesStore();
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PostMapping("/store")
    public Category createCategorieStore(@RequestPart String newCategory, @RequestPart MultipartFile image) {
        Category category = this.createCategory(newCategory);
        String imageUrl;
        try {
            imageUrl = this.uploadServiceFeignClient.uploadImage(image);
            category.setThumbNail(imageUrl);
            return this.feignClient.createCategorieStore(category);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }


    private Category createCategory(String newCategory) {
        CreateCategoryDto categoryDto;
        Category category = new Category();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            categoryDto = objectMapper.readValue(newCategory, CreateCategoryDto.class);
        } catch (IOException io) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, io.getMessage());
        }

        category.setName(categoryDto.getName());

        return category;
    }
}
