package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.Order;
import com.example.marketplaceclient.model.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "https://houlala.herokuapp.com/api/orders", name = "orders")
public interface OrderServiceFeignClient {

    @GetMapping("")
    List<OrderResponse> getAllOrders() throws MarketplaceException;

    @PostMapping("")
    OrderResponse sendOrders(@RequestBody Order newOrder) throws MarketplaceException;

    @GetMapping("/getOrdersFromUsers")
    List<OrderResponse> getOrdersFromUsers(@RequestParam String email) throws MarketplaceException;
}
