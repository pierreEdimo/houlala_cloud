package com.example.marketplaceclient.model.dto;

import com.example.marketplaceclient.model.UserInformation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductCartDto {
    private String locationId;
    private String paymentMode;
    private UserInformation userInformation;
    private CreateItemDto cartItem;
}
