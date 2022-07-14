package com.example.marketplaceclient.model.dto;

import com.example.marketplaceclient.model.CartItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserCart {
    private List<CartItem> cartItemList;
    private int totalQuantity;
    private int totalPrice;

    public UserCart() {
    }

    public UserCart(
            List<CartItem> cartItemList,
            int totalQuantity,
            int totalPrice
    ) {
        this.cartItemList = cartItemList;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }
}
