package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.Order;
import com.example.marketplaceclient.model.UserInformation;
import com.example.marketplaceclient.model.dto.CreateOrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "https://houlala.herokuapp.com/api/order", name = "orders")
public interface OrderServiceFeignClient {

    @GetMapping("")
    List<Order> getAllOrders() throws MarketplaceException;

    @GetMapping("/cartEmails")
    List<Order> getNonConfirmedOrders(@RequestParam String email) throws MarketplaceException;

    @GetMapping("/confirmedOrdersByLocationId")
    List<Order> getConfirmedOrdersByLocationId(@RequestParam String locationId) throws MarketplaceException;

    @GetMapping("/confirmedOrders")
    List<Order> getConfirmedOrdersByLocationIdAndStatus(@RequestParam String locationId, @RequestParam String status) throws MarketplaceException;

    @PutMapping("/sendCommandToSeller")
    void sendCommandToSeller(@RequestBody UserInformation userInformation) throws MarketplaceException;

    @PostMapping("")
    Order addProductToCarts(@RequestBody CreateOrderDto newOrder) throws MarketplaceException;

    @PutMapping("/status/{id}")
    Order updateStatus(@PathVariable(name = "id") String id) throws MarketplaceException;

    @PutMapping("/cancel/{id}")
    Order cancelOrder(@PathVariable(name = "id") String id) throws MarketplaceException;
}
