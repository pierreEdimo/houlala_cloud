package com.example.reviewservice.services;

import com.example.reviewservice.model.Review;
import com.example.reviewservice.model.ReviewResponse;
import com.example.reviewservice.model.ReviewResponseDto;
import com.example.reviewservice.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;

    @Override
    public Review addReview(Review newReview) {

        Review createdReview;

        if (newReview.getRating() > 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "the rating should not be superior to 5");
        } else if (newReview.getRating() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "the rating should be superior or equal to 1");
        } else {
            createdReview = newReview;
        }

        return this.repository.save(createdReview);
    }

    @Override
    public Review editReview(Review newReview, long id) {
        Optional<Review> reviewOptional = this.repository.findById(id);

        if (reviewOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }

        Review existingReview = reviewOptional.get();
        existingReview.setContent(newReview.getContent());
        existingReview.setRating(newReview.getRating());

        return this.repository.save(existingReview);
    }

    @Override
    public void deleteReview(long id) {
        Optional<Review> reviewOptional = this.repository.findById(id);

        if (reviewOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }

        Review existingReview = reviewOptional.get();

        this.repository.delete(existingReview);
    }

    @Override
    public ReviewResponse getASingleReview(long id) {
        Optional<Review> reviewOptional = this.repository.findById(id);

        if (reviewOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }

        Review existingReview = reviewOptional.get();

        return this.toReviewResponse(existingReview);
    }

    @Override
    public List<ReviewResponse> getAllReviews() {
        List<ReviewResponse> responses = new ArrayList<>();
        Iterable<Review> reviewIterable;

        reviewIterable = this.repository.findAll();

        reviewIterable.forEach(review -> responses.add(this.toReviewResponse(review)));

        return responses;
    }

    @Override
    public ReviewResponseDto getReviewByLocationId(int id) {
        double average = 0;
        int sum = 0;
        List<ReviewResponse> reviewResponses = new ArrayList<>();
        ReviewResponseDto reviewResponseDto = new ReviewResponseDto();

        List<Review> reviews = this.repository.findReviewsByLocationId(id);

        for (Review review : reviews) {
            sum += review.getRating();
            reviewResponses.add(this.toReviewResponse(review));
        }

        if (reviews.toArray().length >= 1) {
            average = (float) sum / reviewResponses.size();
        }


        reviewResponseDto.setReviews(reviewResponses);
        reviewResponseDto.setAverageRating(average);

        return reviewResponseDto;

    }

    @Override
    public List<ReviewResponse> getReviewsbyAuthorId(String authorId) {
        List<ReviewResponse> responses = new ArrayList<>();
        List<Review> reviews;

        reviews = this.repository.findReviewsByAuthorId(authorId);

        for (Review review : reviews) {
            responses.add(this.toReviewResponse(review));
        }

        return responses;
    }

    private ReviewResponse toReviewResponse(Review review) {

        ReviewResponse response = new ReviewResponse();

        response.setContent(review.getContent());
        response.setRating(review.getRating());
        response.setId(review.getId());

        return response;
    }
}
