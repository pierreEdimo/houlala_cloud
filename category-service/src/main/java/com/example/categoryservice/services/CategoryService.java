package com.example.categoryservice.services;

import com.example.categoryservice.model.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);

    Category getCategory(long id);

    List<Category> getAllCategories();

    Category editCategory(Category newCategory, long id);

    List<Category> getCategoryStore();

    Category createCategoryStore(Category category);

    void deleteCategory(long id);
}
