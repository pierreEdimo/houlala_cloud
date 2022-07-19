package com.example.marketplaceclient.controller;

import com.example.marketplaceclient.model.UserInformation;
import com.example.marketplaceclient.model.dto.CreateOrderDto;
import com.example.marketplaceclient.model.dto.OrderDto;
import com.example.marketplaceclient.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("")
    public List<OrderDto> getAllOrders() {
        return this.orderService.getAllOrders();
    }

    @GetMapping("/cartItems")
    public List<OrderDto> getCartItems(@RequestParam String email) {
        return this.orderService.getNonConfirmedOrders(email);
    }

    @GetMapping("/confirmedOrdersByLocationId/")
    public List<OrderDto> getConfirmedOrdersByLocationId(@RequestParam String locationId) {
        return this.orderService.getConfirmedOrdersByLocationId(locationId);
    }

    @GetMapping("/confirmedOrdersByLocationIdAndStatus")
    public List<OrderDto> getConfirmedOrdersByLocationIdAndStatus(@RequestParam String locationId, @RequestParam String status) {
        return this.orderService.getConfirmedOrderByLocationIdAndStatus(locationId, status);
    }

    @PutMapping("/confirmOrder")
    public void sendOrderToSeller(@RequestBody UserInformation userInformation) {
        this.orderService.sendCommentToSeller(userInformation);
    }

    @PostMapping("")
    public OrderDto addProductToCartItems(@RequestBody CreateOrderDto newOrder) {
        return this.orderService.addProductToCartItems(newOrder);
    }

    @PutMapping("/status/{id}")
    public void updateStatus(@PathVariable String id) {
        this.orderService.updateOrder(id);
    }

    @PutMapping("/cancel/{id}")
    public void cancelOrder(@PathVariable String id) {
        this.orderService.cancelOrder(id);
    }
}
