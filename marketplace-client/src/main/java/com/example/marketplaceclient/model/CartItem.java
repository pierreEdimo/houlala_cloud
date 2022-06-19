package com.example.marketplaceclient.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem {
    private String userId;

    private String product;

    private int totalPrice;

    private int quantity;

    private String locationId;
}
