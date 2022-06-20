package com.example.marketplaceclient.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SentOrder {
    private String locationId;
    private List<CartItemResponse> cartItems;
    private String status;
    private UserInformation userInformation;
    private Address address;
    private int totalQuantity;
    private int totalPrice;
}
