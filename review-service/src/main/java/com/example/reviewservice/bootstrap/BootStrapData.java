package com.example.reviewservice.bootstrap;

import com.example.reviewservice.model.Review;
import com.example.reviewservice.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BootStrapData implements CommandLineRunner {

    private final ReviewRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Review review = new Review(
                "edimo",
                1,
                "Hello Content",
                4);

        Review review1 = new Review(
                "edimo",
                1,
                "Hello Location 2 is dope",
                3
        );

        this.repository.save(review);
        this.repository.save(review1);
    }
}
