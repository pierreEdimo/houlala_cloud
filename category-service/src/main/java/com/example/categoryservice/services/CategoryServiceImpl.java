package com.example.categoryservice.services;

import com.example.categoryservice.model.Category;
import com.example.categoryservice.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;


    @Override
    public Category createCategory(Category category) {
        return this.repository.save(category);
    }

    @Override
    public Category getCategory(long id) {
        return this.repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
    }

    @Override
    public List<Category> getAllCategories() {
        return this.repository.findCategoriesByStoreCategoryIsFalse();
    }

    @Override
    public Category editCategory(Category newCategory, long id) {
        Category existingCategory = this.repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        existingCategory.setName(newCategory.getName());
        existingCategory.setThumbNail(newCategory.getThumbNail());
        return this.repository.save(existingCategory);
    }

    @Override

    public List<Category> getCategoryStore() {
        return this.repository.findCategoriesByStoreCategoryIsTrue();
    }

    @Override
    public Category createCategoryStore(Category newCategory) {
        newCategory.setStoreCategory(true);
        return this.repository.save(newCategory);
    }

    @Override
    public void deleteCategory(long id) {
        Category existingCategory = this.repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        this.repository.delete(existingCategory);
    }
}
