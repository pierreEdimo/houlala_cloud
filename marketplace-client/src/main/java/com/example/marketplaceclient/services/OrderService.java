package com.example.marketplaceclient.services;

import com.example.marketplaceclient.model.UserInformation;
import com.example.marketplaceclient.model.dto.CreateOrderDto;
import com.example.marketplaceclient.model.dto.OrderDto;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OrderService {

    List<OrderDto> getAllOrders();

    List<OrderDto> getNonConfirmedOrders(@RequestParam String email);

    List<OrderDto> getConfirmedOrdersByLocationId(@RequestParam String locationId);

    List<OrderDto> getConfirmedOrderByLocationIdAndStatus(String locationId, String status);

    void sendCommentToSeller(UserInformation userInformation);

    OrderDto addProductToCartItems(CreateOrderDto orderDto);

    void updateOrder(String id);

    void cancelOrder(String id);

    List<OrderDto> getConfirmedOrders(String email);
}
