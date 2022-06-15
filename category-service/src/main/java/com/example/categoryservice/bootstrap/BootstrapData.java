package com.example.categoryservice.bootstrap;

import com.example.categoryservice.model.Category;
import com.example.categoryservice.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final CategoryRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Category newCategory = new Category(
                "\uD83C\uDFE8",
                "Hotel"
        );

        Category newCategory2 = new Category(
                "\uD83C\uDF7D",
                "Restaurant"
        );

        this.repository.save(newCategory);
        this.repository.save(newCategory2);
    }
}
