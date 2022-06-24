package com.example.marketplaceclient.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateProductDto {
    private String name;

    private String description;

    private int weight;

    private int sellingPrice;

    private String categoryId;

    private String locationId;

    private String productType;

    private int quantity;

    private double buyingPrice;

    private String originLabel;

}
