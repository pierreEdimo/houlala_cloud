package com.houlala.marketplace.order;

import com.houlala.marketplace.information.UserInformation;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();

    void addProducts(@RequestBody CreateOrder order);

    int getTodayOrderCount(String luid);

    int getTodayOrderCountByLocationAndStatus(String luid, String status);

    List<OrderDto> getCartList(String userId);

    OrderDto getSingleOrder(int id);

    int getOrdersCountByLocationAndStatus(String luid, String status);

    int getTotalOrderCount(String luid);

    int getCanceledOrdersCount(String luid);

    int getOrderSoldCount(String luid);

    void confirmOrder(@RequestBody UserInformation userInfo);

    List<OrderDto> getConfirmedOrdersByLocationIdAndStatus(String luid, String status);

    List<OrderDto> getConfirmedOrdersByLocationId(String luid);

    List<OrderDto> getConfirmedUsersOrders(String userId);

    List<OrderDto> getConfirmedOrdersByLocationIdAndLimit(String luid, int size);

    List<OrderDto> getTodayOrderByLocationId(String luid);

    void updateStatusOfOrder(int id);

    void cancelOrder(int id);

    void increaseItemQuantity(int id, String sku);

    void decreaseItemQuantity(int id, String sku);

    void confirmUnregisteredUsersOrders(CreateUnregisteredOrder order);

    void removeItemFromOrder(int id, String sku);

    void deleteOrder(int id);

    void updateDeliveryDate(int id, DeliveryDate date);

    List<SellReport> getTopOrders(String luid);
}
