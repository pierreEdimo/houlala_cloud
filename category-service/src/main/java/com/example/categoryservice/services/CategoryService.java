package com.example.categoryservice.services;

import com.example.categoryservice.model.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category newCategory);

    Category getCategory(long id);

    List<Category> getAllCategories();

    Category editCategory(Category newCategory, long id);

    void deleteCategory(long id);
}
