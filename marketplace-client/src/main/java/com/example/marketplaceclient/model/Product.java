package com.example.marketplaceclient.model;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private String name;
    private String description;
    private int weight;
    private String imageUrl;
    private int sellingPrice;
    private String locationId;
    private boolean bookMarked;
    private String productSku;
}

