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
    private int sellingPrice;
    private String locationId;
    private boolean bookMarked;
    private String productSku;



    public ProductResponse(){}

    public ProductResponse(
            String name,
            String description,
            int weight,
            int sellingPrice,
            String locationId,
            String productSku
    ){
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.sellingPrice = sellingPrice;
        this.locationId = locationId;
        this.productSku = productSku;
    }
}

