package com.example.categoryservice.repositories;

import com.example.categoryservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findCategoriesByStoreCategoryIsTrue();
}
