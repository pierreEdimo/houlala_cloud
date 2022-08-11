package com.example.notificationclient.feign;

import com.example.notificationclient.exception.NotificationException;
import com.example.notificationclient.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(path = "marketplace", name = "marketplace")
public interface OrderFeignClient {

    @GetMapping("/orders/carts")
    List<Order> getUnconfirmedOrders(@RequestParam String userId) throws NotificationException;
}
