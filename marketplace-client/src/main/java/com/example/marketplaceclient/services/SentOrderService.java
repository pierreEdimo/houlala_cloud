package com.example.marketplaceclient.services;

import com.example.marketplaceclient.model.Order;
import com.example.marketplaceclient.model.dto.CreateProductCartDto;
import com.example.marketplaceclient.model.dto.OrderDto;
import com.example.marketplaceclient.model.dto.UserCart;

import java.util.List;

public interface SentOrderService {
    List<OrderDto> getOrdersByLocationId(String locationId);

    Order addProductToCart(CreateProductCartDto newCart);

    UserCart getTheCartByUserEmail(String email);
}
