package com.example.discoveryorchestrator.feign;

import com.example.discoveryorchestrator.exception.OrchestratorException;
import com.example.discoveryorchestrator.model.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "category", path = "category")
public interface CategoryServiceFeignClient {

    @GetMapping("/getSingleCategory/{id}")
    Category getCategory(@PathVariable long id) throws OrchestratorException;

    @PostMapping("/newCategory")
    Category createCategory(@RequestBody Category newCategory) throws OrchestratorException;

    @GetMapping("")
    List<Category> getAllCategories() throws OrchestratorException;

    @PutMapping("/editCategory/{id}")
    Category editCategory(@RequestBody Category newCategory, @PathVariable long id) throws OrchestratorException;

    @DeleteMapping("/deleteCategory/{id}")
    void deleteCategory(@PathVariable long id) throws OrchestratorException;
}
