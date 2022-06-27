package com.example.marketplaceclient.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {

    private String _id;

    private String userId;

    private Product product;

    private int totalPrice;

    private int quantity;

    private String locationId;
}
