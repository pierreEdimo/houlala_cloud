package com.example.marketplaceclient.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Order {
    private int id;
    private String status;
    private String locationId;
    private boolean confirmed;
    private String payMentMode;
    private String createdAt;
    private String updatedAt;
    private List<CartItem> cartItems;
    private UserInformation userInformation;
    private String deliveryDate;
}
