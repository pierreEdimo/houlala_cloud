package com.example.orchestrator.controller;

import com.example.orchestrator.exception.OrchestratorException;
import com.example.orchestrator.feign.ReviewServiceFeignClient;
import com.example.orchestrator.model.ReviewDto;
import com.example.orchestrator.model.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class OrchestratorReviewController {

    private final ReviewServiceFeignClient feignClient;

    @GetMapping("/getReview/{id}")
    public ReviewResponse getAReview(@PathVariable long id) {
        try {
            return this.feignClient.getSingleReview(id);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("")
    public List<ReviewResponse> getAllReviews() {
        try {
            return this.feignClient.getAllReviews();
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @DeleteMapping("/deleteReview/{id}")
    public void deleteReview(@PathVariable long id) {
        try {
            this.feignClient.deleteReview(id);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PutMapping("/editReview/{id}")
    public ReviewDto editReview(@RequestBody ReviewDto reviewDto, @PathVariable long id) {
        try {
            return this.feignClient.editReview(reviewDto, id);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PostMapping("/newReview")
    public ReviewDto addReview(@RequestBody ReviewDto reviewDto){
        try {
            return this.feignClient.addReview(reviewDto);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/getReviewsByAuthorId")
    public List<ReviewResponse> getReviewsByAuthorId(@RequestParam String authorId){
        try {
            return this.feignClient.getReviewsByAuthorId(authorId);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

}
