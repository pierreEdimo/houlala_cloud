package com.example.reviewservice.services;

import com.example.reviewservice.model.Review;
import com.example.reviewservice.model.ReviewResponse;
import com.example.reviewservice.model.ReviewResponseDto;

import java.util.List;

public interface ReviewService {

    Review addReview(Review newReview);

    Review editReview(Review newReview, long id);

    void deleteReview(long id);

    ReviewResponse getASingleReview(long id);

    List<ReviewResponse> getAllReviews();

    ReviewResponseDto getReviewByLocationId(int id);

    List<ReviewResponse> getReviewsbyAuthorId(String authorId);
}
