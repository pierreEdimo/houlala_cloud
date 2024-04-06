package com.houlala.marketplace.orders;

import java.util.List;

import com.houlala.marketplace.information.UserInformation;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateOrder{
    private String locationUniqueId; 
    private int PaymentMethodId; 
    private UserInformation userInformation; 
    private List<CartItem> items;
}