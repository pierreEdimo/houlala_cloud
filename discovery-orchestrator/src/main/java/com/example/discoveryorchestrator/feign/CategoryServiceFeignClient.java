package com.example.discoveryorchestrator.feign;

import com.example.discoveryorchestrator.exception.OrchestratorException;
import com.example.discoveryorchestrator.model.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "category", path = "category")
public interface CategoryServiceFeignClient {

    @GetMapping("/{id}")
    Category getCategory(@PathVariable(value = "id") long id) throws OrchestratorException;

    @PostMapping(value = "/newCategory")
    Category createCategory(@RequestBody Category category) throws OrchestratorException;

    @GetMapping("")
    List<Category> getAllCategories() throws OrchestratorException;

    @PutMapping("/{id}")
    Category editCategory(@RequestBody Category newCategory, @PathVariable long id) throws OrchestratorException;

    @DeleteMapping("/{id}")
    void deleteCategory(@PathVariable(value = "id") long id) throws OrchestratorException;

    @GetMapping("/store")
    List<Category> getCategoriesStore() throws OrchestratorException;

    @PostMapping("/store")
    Category createCategorieStore(@RequestBody Category newCategory) throws OrchestratorException;
}
