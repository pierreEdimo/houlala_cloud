package com.example.reviewservice.repositories;

import com.example.reviewservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findReviewsByLocationId(int locationId);

    List<Review> findReviewsByAuthorId(String authorId);


}
