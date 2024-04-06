package com.houlala.marketplace.orders;

import java.sql.Date;
import java.util.List;

import com.houlala.marketplace.information.UserInformation;
import com.houlala.marketplace.location.Location;

import lombok.*; 

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {
    private int id;
    private String orderNumber;
    private Date deliveryDate;
    private String status; 
    private boolean confirmed; 
    private Date createdAt; 
    private Date updatedAt; 
    private UserInformation user; 
    private boolean canceled; 
    private int totalPrice;
    private int totalQuantityOfItems;
    private List<CartItem> items; 
    private Location location; 
}
