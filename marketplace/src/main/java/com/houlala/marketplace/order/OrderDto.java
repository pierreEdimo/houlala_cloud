package com.houlala.marketplace.order;

import com.houlala.marketplace.information.UserInformation;
import com.houlala.marketplace.location.Location;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {
    private int id;
    private UserInformation userInformation;
    private Location location;
    private LocalDate deliveryDate;
    private String status;
    private boolean confirmed;
    private String payMentMethode;
    private Date createdAt;
    private Date updatedAt;
    private List<CartItem> cartItems;
    private int totalQuantity;
    private int totalPrice;
}
