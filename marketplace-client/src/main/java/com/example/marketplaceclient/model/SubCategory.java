package com.example.marketplaceclient.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubCategory {
    private int id;
    private String label;
    private String thumbNailUrl;
    private int categoryId;

    public SubCategory() {
    }

    public SubCategory(String label, int categoryId) {
        this.label = label;
        this.categoryId = categoryId;
    }
}
