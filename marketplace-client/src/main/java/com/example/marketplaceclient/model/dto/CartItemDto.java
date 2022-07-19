package com.example.marketplaceclient.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
    private String productSku;
    private String product;
    private String imageUrl;
    private int quantity;
    private int price;

    public CartItemDto(
            String productSku,
            String product,
            String imageUrl,
            int quantity,
            int price
    ){
        this.productSku = productSku;
        this.product = product;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
        this.price = price;
    }
}
