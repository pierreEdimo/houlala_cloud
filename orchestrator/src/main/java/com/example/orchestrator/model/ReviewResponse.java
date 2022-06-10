package com.example.orchestrator.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResponse {
    private long id;

    private String content;

    private int rating;
}
