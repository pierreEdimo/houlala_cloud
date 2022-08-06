package com.example.marketplaceclient.model.dto;

import com.example.marketplaceclient.model.CartItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateOrderDto {
    private String userId;
    private String locationId;
    private List<CartItem> cartItems;
}
