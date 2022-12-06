package com.example.marketplaceclient.model;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String _id;
    private String name;
    private String description;
    private int weight;
    private String imageUrl;
    private int sellingPrice;
    private String locationId;
    private boolean bookMarked;
    private String productSku;
}

