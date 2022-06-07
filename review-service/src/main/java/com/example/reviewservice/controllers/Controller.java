package com.example.reviewservice.controllers;

import com.example.reviewservice.model.Review;
import com.example.reviewservice.model.ReviewResponse;
import com.example.reviewservice.model.ReviewResponseDto;
import com.example.reviewservice.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final ReviewService service;

    @GetMapping("/getSingleReview/{id}")
    public ReviewResponse getAReview(@PathVariable long id) {
        return this.service.getASingleReview(id);
    }

    @PostMapping("/newReview")
    public Review addReview(@RequestBody Review newReview) {
        return this.service.addReview(newReview);
    }

    @GetMapping("")
    public List<ReviewResponse> getAllReviews() {
        return this.service.getAllReviews();
    }

    @PutMapping("/editReview/{id}")
    public Review editReview(@RequestBody Review newReview, @PathVariable long id) {
        return this.service.editReview(newReview, id);
    }

    @DeleteMapping("/deleteReview/{id}")
    public void deleteReview(@PathVariable long id) {
        this.service.deleteReview(id);
    }

    @GetMapping("/getAllReviewByLocationId/{locationId}")
    public ReviewResponseDto getAllReviewByLocationId(@PathVariable int locationId) {
        return this.service.getReviewByLocationId(locationId);
    }
}
