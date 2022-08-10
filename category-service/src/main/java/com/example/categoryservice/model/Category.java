package com.example.categoryservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String thumbNail;
    private String name;

    private boolean storeCategory = false;

    public Category() {
    }

    public Category(String thumbNail, String name) {
        this.name = name;
        this.thumbNail = thumbNail;
    }

    public Category(String thumbNail,
                    String name,
                    boolean storeCategory
    ) {
        this.name = name;
        this.thumbNail = thumbNail;
        this.storeCategory = storeCategory;
    }
}
