package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.Order;
import com.example.marketplaceclient.model.UserInformation;
import com.example.marketplaceclient.model.dto.CreateOrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "https://houlala.herokuapp.com/api/orders", name = "orders")
public interface OrderServiceFeignClient {

    @GetMapping("")
    List<Order> getAllOrders() throws MarketplaceException;

    @GetMapping("/carts")
    List<Order> getNonConfirmedOrders(@RequestParam String userId) throws MarketplaceException;

    @GetMapping("/confirmed")
    List<Order> getConfirmedOrders(@RequestParam String userId) throws MarketplaceException;

    @GetMapping("/confirmed/location/{id}")
    List<Order> getConfirmedOrdersByLocationId(@PathVariable("id") String locationId) throws MarketplaceException;

    @GetMapping("/confirmed/location/{id}/status/{status}")
    List<Order> getConfirmedOrdersByLocationIdAndStatus(@PathVariable("id") String locationId, @PathVariable("status") String status) throws MarketplaceException;

    @PutMapping("/command")
    void sendCommandToSeller(@RequestBody UserInformation userInformation) throws MarketplaceException;

    @PostMapping("")
    Order addProductToCarts(@RequestBody CreateOrderDto newOrder) throws MarketplaceException;

    @PutMapping("/status/{id}")
    Order updateStatus(@PathVariable(name = "id") String id) throws MarketplaceException;

    @PutMapping("/cancel/{id}")
    Order cancelOrder(@PathVariable(name = "id") String id) throws MarketplaceException;

    @PutMapping("/cartItems/increase/{id}/sku/{sku}")
    void increaseQuantity(@PathVariable String id, @PathVariable String sku) throws MarketplaceException;

    @PutMapping("/cartItems/decrease/{id}/sku/{sku}")
    void decreaseQuantity(@PathVariable String id, @PathVariable String sku) throws MarketplaceException;

    @DeleteMapping("cartItems/{id}/sku/{sku}")
    void deleteItemFromOrder(@PathVariable String id, @PathVariable String sku) throws MarketplaceException;
}
