package com.example.reviewservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authorId;

    private int locationId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int rating;
}
