package com.example.categoryservice.bootstrap;

import com.example.categoryservice.model.Category;
import com.example.categoryservice.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {

    }
}
