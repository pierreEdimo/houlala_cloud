package com.example.categoryservice.controller;

import com.example.categoryservice.model.Category;
import com.example.categoryservice.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final CategoryService service;

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable long id) {
        return this.service.getCategory(id);
    }

    @PostMapping("")
    public Category createCategory(@RequestPart String name, @RequestPart MultipartFile image) {
        return this.service.createCategory(name, image);
    }

    @GetMapping("/store")
    public List<Category> getCategorieStore() {
        return this.service.getCategoryStore();
    }

    @PostMapping("/store")
    public Category createCategorieStore(@RequestPart String newCategory, @RequestPart MultipartFile image) {
        return this.service.createCategoryStore(newCategory, image);
    }

    @GetMapping("")
    public List<Category> getAllCategories() {
        return this.service.getAllCategories();
    }

    @PutMapping("/{id}")
    public Category editCategory(@RequestBody Category newCategory, @PathVariable long id) {
        return this.service.editCategory(newCategory, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable long id) {
        this.service.deleteCategory(id);
    }
}
