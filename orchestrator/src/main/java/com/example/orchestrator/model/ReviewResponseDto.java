package com.example.orchestrator.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReviewResponseDto {

    private List<ReviewResponse> reviews;

    private double averageRating;
}
