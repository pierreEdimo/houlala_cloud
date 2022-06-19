package com.example.marketplaceclient.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Order {
    private List<CartItem> cartItems;

    private int totalQuantity;

    private int totalPrice;

    private String status;

    private String payMentMode;

    private String orderOptions;

    private UserInformation userInformation;

    private Address address;
}
