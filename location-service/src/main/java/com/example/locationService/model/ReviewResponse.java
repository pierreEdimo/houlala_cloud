package com.example.locationService.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResponse {
    private String content;

    private long id;

    private int rating;
}
