package com.houlala.marketplace.order;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateOrder {
    private String userId;
    private String locationUniqueId;
    private List<CartItem> cartItems;
}
