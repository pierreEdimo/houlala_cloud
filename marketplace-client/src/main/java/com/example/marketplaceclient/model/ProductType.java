package com.example.marketplaceclient.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductType {
    private String _id;
    private String label;
    private String thumbNailUrl;
    private String categoryId;

    public ProductType() {
    }

    public ProductType(String label, String categoryId) {
        this.label = label;
        this.categoryId = categoryId;
    }
}
