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
                "Hotel",
                false
        );

        Category newCategory2 = new Category(
                "\uD83C\uDF7D",
                "Restaurant",
                false
        );

        Category newCategory3 = new Category(
                "\uD83C\uDF4C",
                "Fruits & Legumes",
                true
        );

        Category newCategory4 = new Category(
                "\uD83D\uDCBB",
                "Electroniques",
                true
        );

        this.repository.save(newCategory);
        this.repository.save(newCategory2);
        this.repository.save(newCategory3);
        this.repository.save(newCategory4);
    }
}
