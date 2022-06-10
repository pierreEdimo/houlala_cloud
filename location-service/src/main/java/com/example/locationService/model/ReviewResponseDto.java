package com.example.locationService.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReviewResponseDto {

    private List<ReviewResponse> reviews;

    private double averageRating;
}
