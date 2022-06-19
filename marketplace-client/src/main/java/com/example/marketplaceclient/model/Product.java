package com.example.marketplaceclient.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String name;
    private String description;
    private int weight;
    private int sellingPrice;
    private long locationId;
    private String productType;
    private String categoryId;
}
