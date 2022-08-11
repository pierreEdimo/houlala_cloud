package com.example.notificationclient.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Order {
    private String status;
    private String payMentMode;
    private int totalQuantity;
    private int totalPrice;
    private List<CartItem> cartItems;
}
