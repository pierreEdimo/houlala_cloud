package com.example.marketplaceclient.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Order {
   private String locationId;
   private String paymentMode;
   private int totalQuantity;
   private int totalPrice;
   private List<CartItem> cartItems;
   private UserInformation userInformation;
}
