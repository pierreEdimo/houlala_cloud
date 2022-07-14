package com.example.marketplaceclient.controller;

import com.example.marketplaceclient.model.Order;
import com.example.marketplaceclient.model.dto.CreateProductCartDto;
import com.example.marketplaceclient.model.dto.OrderDto;
import com.example.marketplaceclient.model.dto.UserCart;
import com.example.marketplaceclient.services.SentOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/sent-orders")
public class SentOrderController {

    private final SentOrderService orderService;

    @GetMapping("/getOrdersByLocationId")
    public List<OrderDto> getOrdersByLocationId(@RequestParam String locationId) {
        return this.orderService.getOrdersByLocationId(locationId);
    }

    @PostMapping("/newCart")
    @ResponseStatus(HttpStatus.CREATED)
    public Order addProductToCart(@RequestBody CreateProductCartDto newCart) {
        return this.orderService.addProductToCart(newCart);
    }

    @GetMapping("/filterCarts")
    public UserCart getUserCartsByEmail(@RequestParam String email) {
        return this.orderService.getTheCartByUserEmail(email);
    }

}
