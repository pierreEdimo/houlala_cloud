package com.example.marketplaceclient.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductResponse {
    private String _id;
    private String name;
    private String description;
    private int weight;
    private String imageUrl;
    private int initialPrice;
    private long locationId;
}

