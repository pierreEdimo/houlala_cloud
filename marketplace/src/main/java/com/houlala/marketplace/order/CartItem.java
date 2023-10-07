package com.houlala.marketplace.order;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartItem {
    private String productSku;
    private int quantity;
    private int price;
    private int initialPrice;
    private String product;
    private String imageUrl;
}
