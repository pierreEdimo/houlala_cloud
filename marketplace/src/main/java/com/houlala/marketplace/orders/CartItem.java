package com.houlala.marketplace.orders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartItem{
    private String productSku;
    private int price; 
    private int initialPrice; 
    private String imageUrl; 
    private int quantity; 
    private String product; 
}