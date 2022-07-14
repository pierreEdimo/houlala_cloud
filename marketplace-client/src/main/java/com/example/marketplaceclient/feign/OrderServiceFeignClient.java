package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.CartItem;
import com.example.marketplaceclient.model.Order;
import com.example.marketplaceclient.model.dto.CreateProductCartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "https://houlala.herokuapp.com/api/sent-orders", name = "sent-orders")
public interface OrderServiceFeignClient {


    @GetMapping("/filterOrdersByLocationId")
    List<Order> fetchOrderFromLocationId(@RequestParam String locationId) throws MarketplaceException;

    @PostMapping("")
    Order addProductToCart(@RequestBody CreateProductCartDto newProduct) throws MarketplaceException;

    @GetMapping("/filterCartItems")
    List<CartItem> getCartsByEmail(@RequestParam String email) throws MarketplaceException;

}
