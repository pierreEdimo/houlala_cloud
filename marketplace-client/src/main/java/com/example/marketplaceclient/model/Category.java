package com.example.marketplaceclient.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
    private int id;

    private String name;

    private String imageUrl;

    private String description;

    public Category() {
    }

    public Category(
            String name,
            String description
    ) {
        this.name = name;
        this.description = description;
    }
}
