package com.example.notificationclient.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItem {
    private String productSku;
    private int price;
    private String product;
    private String imageUrl;
    private int quantity;
}
