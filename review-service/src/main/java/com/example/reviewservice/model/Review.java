package com.example.reviewservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Review {

    public Review() {
    }

    public Review(String authorId, String locationId, String content, int rating) {
        this.authorId = authorId;
        this.locationId = locationId;
        this.content = content;
        this.rating = rating;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authorId;

    private String locationId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int rating;

}
