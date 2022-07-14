package com.example.marketplaceclient.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem {
    private String name;
    private String description;
    private String imageUrl;
    private int totalPrice;
    private int totalQuantity;
    private String productSku;

    public CartItem() {}

    public CartItem(
            String name,
            String description,
            String imageUrl,
            int totalPrice,
            int totalQuantity,
            String productSku
    ){
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
        this.productSku = productSku;
    }
}
