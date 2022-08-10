package com.example.categoryservice.services;

import com.example.categoryservice.exception.CategoryException;
import com.example.categoryservice.feign.UploadFeignService;
import com.example.categoryservice.model.Category;
import com.example.categoryservice.model.dto.CreateCategoryDto;
import com.example.categoryservice.repositories.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    private final UploadFeignService feignService;

    @Override
    public Category createCategory(String newCategory, MultipartFile image) {
        CreateCategoryDto createCategoryDto;
        String imageUrl;
        Category category;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            createCategoryDto = objectMapper.readValue(newCategory, CreateCategoryDto.class);
        } catch (IOException io) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, io.getMessage());
        }

        try {
            imageUrl = this.feignService.uploadImage(image);
            category = new Category(imageUrl,
                    createCategoryDto.getName()
            );
        } catch (CategoryException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        return this.repository.save(category);
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
        return this.repository.findCategoriesByStoreCategoryIsFalse();
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
    public Category createCategoryStore(String newCategoryStore, MultipartFile image) {
        CreateCategoryDto createCategoryDto;
        String imageUrl;
        Category category;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            createCategoryDto = objectMapper.readValue(newCategoryStore, CreateCategoryDto.class);
        } catch (IOException io) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, io.getMessage());
        }

        try {
            imageUrl = this.feignService.uploadImage(image);
            category = new Category(imageUrl,
                    createCategoryDto.getName()
            );
            category.setStoreCategory(true);
        } catch (CategoryException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        return this.repository.save(category);
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
