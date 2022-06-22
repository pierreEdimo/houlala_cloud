package com.example.marketplaceclient.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProduct {
    private String name;
    private String description;
    private int weight;
    private String imageUrl;
    private int sellingPrice;
    private String locationId;
    private boolean bookMarked;
    private String productSku;

    private String categoryId;

    private String productType;

    public CreateProduct(){}

    public CreateProduct(
            String name,
            String description,
            int weight,
            int sellingPrice,
            String locationId,
            String productSku,
            String categoryId,
            String productType
    ){
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.sellingPrice = sellingPrice;
        this.locationId = locationId;
        this.productSku = productSku;
        this.categoryId = categoryId;
        this.productType = productType;
    }
}
