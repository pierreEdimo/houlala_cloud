package com.example.marketplaceclient.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemResponse {

    private String _id;

    private String userId;

    private ProductResponse product;

    private int totalPrice;

    private int quantity;

    private String locationId;
}
