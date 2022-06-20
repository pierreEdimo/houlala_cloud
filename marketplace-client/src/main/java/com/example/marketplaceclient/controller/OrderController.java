package com.example.marketplaceclient.controller;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.feign.OrderServiceFeignClient;
import com.example.marketplaceclient.model.Order;
import com.example.marketplaceclient.model.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderServiceFeignClient feignClient;

    @GetMapping("")
    public List<OrderResponse> getAllOrders() {
        try {
            return this.feignClient.getAllOrders();
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PostMapping("")
    public OrderResponse sendOrder(@RequestBody Order newOrder){
        try {
            return this.feignClient.sendOrders(newOrder);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/getOrdersFromUsers")
    public List<OrderResponse> getOrdersFromUsers(@RequestParam String email){
        try {
            return this.feignClient.getOrdersFromUsers(email);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }
}
