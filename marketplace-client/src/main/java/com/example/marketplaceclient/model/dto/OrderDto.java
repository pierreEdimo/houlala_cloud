package com.example.marketplaceclient.model.dto;

import com.example.marketplaceclient.model.CartItem;
import com.example.marketplaceclient.model.UserInformation;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class OrderDto {
    private String locationId;
    private String paymentMode;
    private int totalQuantity;
    private int totalPrice;
    Map<String, CartItem> cartItems;
    private UserInformation userInformation;

    public OrderDto(){}

    public OrderDto(
            String locationId,
            String paymentMode,
            int totalPrice,
            int totalQuantity,
            Map<String, CartItem> cartItems,
            UserInformation userInformation
    ){
        this.locationId = locationId;
        this.paymentMode = paymentMode;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
        this.cartItems = cartItems;
        this.userInformation = userInformation;
    }
}
