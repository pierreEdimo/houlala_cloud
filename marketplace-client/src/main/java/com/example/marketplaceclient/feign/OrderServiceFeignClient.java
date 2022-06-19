package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url = "https://houlala.herokuapp.com/api/orders", name = "orders")
public interface OrderServiceFeignClient {

    @GetMapping("")
    List<Order> getAllOrders() throws MarketplaceException;
}
