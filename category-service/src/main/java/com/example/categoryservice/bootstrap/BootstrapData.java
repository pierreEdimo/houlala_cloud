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
                "https://images.unsplash.com/photo-1519996529931-28324d5a630e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80",
                "Fruits & Legumes",
                true
        );

        Category newCategory4 = new Category(
                "https://images.unsplash.com/photo-1593344484962-796055d4a3a4?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2030&q=80",
                "Electroniques",
                true
        );

        this.repository.save(newCategory);
        this.repository.save(newCategory2);
        this.repository.save(newCategory3);
        this.repository.save(newCategory4);
    }
}
