package com.example.marketplaceclient.services;

import com.example.marketplaceclient.model.DeliveryDate;
import com.example.marketplaceclient.model.UserInformation;
import com.example.marketplaceclient.model.dto.CreateOrderDto;
import com.example.marketplaceclient.model.dto.CreateUnregisteredUserOrder;
import com.example.marketplaceclient.model.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> getAllOrders();

    List<OrderDto> getNonConfirmedOrders(String userId);

    List<OrderDto> getConfirmedOrdersByLocationId(String locationId);

    List<OrderDto> getConfirmedOrderByLocationIdAndStatus(String locationId, String status);

    void sendCommentToSeller(UserInformation userInformation);

    OrderDto addProductToCartItems(CreateOrderDto orderDto);

    void updateOrder(String id);

    void cancelOrder(String id);

    List<OrderDto> getConfirmedOrders(String userId);

    void increaseQuantity(String id, String sku);

    void decreaseQuantity(String id, String sku);

    void deleteItemFromOrder(String id, String sku);

    OrderDto sendUnregisteredUserOrder(CreateUnregisteredUserOrder order);

    long getOrderTotalCount(String locationId);

    long getOrderSoldCount(String locationId);

    long getOrderCanceledCount(String locationId);

    List<OrderDto> getAllOrdersByLocationId(String locationId);

    void updateDeliveryDate(String id, DeliveryDate newDate);
}
