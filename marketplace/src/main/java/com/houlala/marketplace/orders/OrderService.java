package com.houlala.marketplace.orders;

import java.util.List;

public interface OrderService {
    List<OrderDto>  getUsersOrders(String userId); 
    List<OrderDto> getLocationsOrders(String locationId);
    void addProductToCart(CreateOrder order);
    void removeProductFromCart(String orderNumber, String productSku);
    void increaseItemQuantity(String orderNumber, String productSku);
    void decreaseItemQuantity(String orderNumber, String productSku);
    void updateStatusOfAnOrder(String orderNumber);
    void confirmOrders(String userId, int paymentMethodId, String sellerEmail );
    void confirmGastOrders(List<CreateOrder> orders, String sellerEmail);
    void cancelOrder(String orderNumber);
    OrderDto getSingleOrder(String ordeNumber);
}
