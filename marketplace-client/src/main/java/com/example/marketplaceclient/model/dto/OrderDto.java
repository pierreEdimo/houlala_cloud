package com.example.marketplaceclient.model.dto;

import com.example.marketplaceclient.model.Location;
import com.example.marketplaceclient.model.UserInformation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDto {
    private String _id;
    private String status;
    private boolean confirmed;
    private String payMentMode;
    private String createdAt;
    private String updatedAt;
    private UserInformation userInformation;
    private List<CartItemDto> cartItems;
    private int totalQuantity;
    private int totalPrice;
    private Location location;
    private String deliveryDate;


    public OrderDto(
            String _id,
            String status,
            boolean confirmed,
            String payMentMode,
            String createdAt,
            String updatedAt,
            UserInformation userInformation,
            List<CartItemDto> cartItems,
            Location location,
            String deliveryDate
    ) {
        this._id = _id;
        this.status = status;
        this.confirmed = confirmed;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userInformation = userInformation;
        this.cartItems = cartItems;
        this.payMentMode = payMentMode;
        this.calculateToTalQuantity();
        this.calculateTotalPrice();
        this.location = location;
        this.deliveryDate = deliveryDate;
    }

    private void calculateToTalQuantity() {
        for(CartItemDto cartItemDto: cartItems)
            this.totalQuantity += cartItemDto.getQuantity();
    }

    private void calculateTotalPrice(){
        for(CartItemDto cartItemDto: cartItems){
            this.totalPrice += cartItemDto.getPrice();
        }
    }
}
