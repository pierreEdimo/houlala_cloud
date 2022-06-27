package com.example.marketplaceclient.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderDto {
    private List<CartItemDto> cartItems;

    private int totalQuantity;

    private int totalPrice;

    private String status;

    private String payMentMode;

    private String orderOptions;

    private UserInformation userInformation;

    private Address address;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
