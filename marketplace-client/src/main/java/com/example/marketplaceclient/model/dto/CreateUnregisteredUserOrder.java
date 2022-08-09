package com.example.marketplaceclient.model.dto;

import com.example.marketplaceclient.model.CartItem;
import com.example.marketplaceclient.model.UserInformation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateUnregisteredUserOrder {
    private UserInformation userInformation;
    private String locationId;
    private List<CartItem> cartItems;
}
