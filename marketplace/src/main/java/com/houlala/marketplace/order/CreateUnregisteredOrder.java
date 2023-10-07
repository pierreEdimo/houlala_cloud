package com.houlala.marketplace.order;

import com.houlala.marketplace.information.UserInformation;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateUnregisteredOrder {
    private UserInformation userInformation;
    private String locationUniqueId;
    private List<CartItem> cartItems;
}
