package com.example.orchestrator.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {

    private long id;

    private String authorId;

    private int locationId;

    private String content;

    private int rating;
}
