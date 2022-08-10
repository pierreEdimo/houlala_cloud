package com.example.marketplaceclient.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductType {
    private String _id;
    private String label;
    private String thumbNailUrl;

    public ProductType() {
    }

    public ProductType(String label) {
        this.label = label;
    }
}
