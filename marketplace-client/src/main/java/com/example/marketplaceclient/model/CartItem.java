package com.example.marketplaceclient.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem {
    private String productSku;
    private int quantity;
    private int price;
    private String _id;
}
