package com.example.categoryservice.services;

import com.example.categoryservice.model.Category;
import com.example.categoryservice.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public Category createCategory(Category newCategory) {
        return this.repository.save(newCategory);
    }

    @Override
    public Category getCategory(long id) {
        Optional<Category> category = this.repository.findById(id);

        if (category.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }

        return category.get();
    }

    @Override
    public List<Category> getAllCategories() {
        return this.repository.findAll();
    }

    @Override
    public Category editCategory(Category newCategory, long id) {
        Optional<Category> category = this.repository.findById(id);

        if (category.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }

        Category existingCategory = category.get();

        existingCategory.setName(newCategory.getName());
        existingCategory.setThumbNail(newCategory.getThumbNail());

        return this.repository.save(existingCategory);
    }

    @Override
    public List<Category> getCategoryStore() {
        return this.repository.findCategoriesByStoreCategoryIsTrue();
    }

    @Override
    public Category createCategoryStore(Category newCategoryStore) {
        newCategoryStore.setStoreCategory(true);
        return this.repository.save(newCategoryStore);
    }

    @Override
    public void deleteCategory(long id) {
        Optional<Category> category = this.repository.findById(id);

        if (category.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }

        Category existingCategory = category.get();

        this.repository.delete(existingCategory);
    }
}
