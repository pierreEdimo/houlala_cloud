package com.example.categoryservice.services;

import com.example.categoryservice.model.Category;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
    Category createCategory(String newCategory, MultipartFile image);

    Category getCategory(long id);

    List<Category> getAllCategories();

    Category editCategory(Category newCategory, long id);

    List<Category> getCategoryStore();

    Category createCategoryStore(String newCategory, MultipartFile image);

    void deleteCategory(long id);
}
