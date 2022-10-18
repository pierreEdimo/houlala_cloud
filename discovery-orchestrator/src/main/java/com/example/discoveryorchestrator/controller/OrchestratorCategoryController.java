package com.example.discoveryorchestrator.controller;

import com.example.discoveryorchestrator.exception.OrchestratorException;
import com.example.discoveryorchestrator.feign.CategoryServiceFeignClient;
import com.example.discoveryorchestrator.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class OrchestratorCategoryController {

    private final CategoryServiceFeignClient feignClient;

    @PostMapping("")
    public Category createCategory(@RequestPart String newCategory, @RequestPart MultipartFile image) {
        try {
            return this.feignClient.createCategory(newCategory, image);
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
    public Category createCategorieStore(@RequestBody Category newCategory) {
        try {
            return this.feignClient.createCategorieStore(newCategory);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }
}
