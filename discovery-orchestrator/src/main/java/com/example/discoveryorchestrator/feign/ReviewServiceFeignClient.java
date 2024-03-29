package com.example.discoveryorchestrator.feign;

import com.example.discoveryorchestrator.exception.OrchestratorException;
import com.example.discoveryorchestrator.model.ReviewDto;
import com.example.discoveryorchestrator.model.ReviewResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "review", path = "review")
public interface ReviewServiceFeignClient {

    @GetMapping("/getSingleReview/{id}")
    ReviewResponse getSingleReview(@PathVariable(value = "id") long id) throws OrchestratorException;

    @GetMapping("")
    List<ReviewResponse> getAllReviews() throws OrchestratorException;

    @DeleteMapping("/deleteReview/{id}")
    void deleteReview(@PathVariable(value = "id") long id) throws OrchestratorException;

    @PutMapping("/editReview/{id}")
    ReviewDto editReview(@RequestBody ReviewDto reviewDto, @PathVariable long id) throws OrchestratorException;

    @PostMapping("/newReview")
    ReviewDto addReview(@RequestBody ReviewDto reviewDto) throws OrchestratorException;

    @GetMapping("/getAllReviewsByAuthorId")
    List<ReviewResponse> getReviewsByAuthorId(@RequestParam(name = "authorId") String authorId) throws OrchestratorException;
}
