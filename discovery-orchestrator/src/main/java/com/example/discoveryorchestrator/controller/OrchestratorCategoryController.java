package com.example.discoveryorchestrator.controller;

import com.example.discoveryorchestrator.exception.OrchestratorException;
import com.example.discoveryorchestrator.feign.CategoryServiceFeignClient;
import com.example.discoveryorchestrator.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class OrchestratorCategoryController {

    private final CategoryServiceFeignClient feignClient;

    @PostMapping("/newCategory")
    public Category createCategory(@RequestBody Category newCategory) {
        try {
            return this.feignClient.createCategory(newCategory);
        } catch (OrchestratorException ex) {
            throw new ResponseStatusException(ex.getHttpStatus(), ex.getMessage());
        }
    }

    @DeleteMapping("/deleteCategory/{id}")
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

    @PutMapping("/editCategory/{id}")
    public Category editCategory(@RequestBody Category newCategory, @PathVariable long id) {
        try {
            return this.feignClient.editCategory(newCategory, id);
        } catch (OrchestratorException ex) {
            throw new ResponseStatusException(ex.getHttpStatus(), ex.getMessage());
        }
    }

    @GetMapping("/getSingleCategory/{id}")
    public Category getSingleCategory(@PathVariable long id) {
        try {
            return this.feignClient.getCategory(id);
        } catch (OrchestratorException ex) {
            throw new ResponseStatusException(ex.getHttpStatus(), ex.getMessage());
        }
    }
}
