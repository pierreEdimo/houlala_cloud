package com.houlala.marketplace.orders;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.houlala.marketplace.exception.MarketplaceException;

@FeignClient(name = "order", url = "https://order-api.houlala.store/api/orders")
public interface OrderFeign {

    @GetMapping("users/{userid}")
    List<Order> getUsersOrders(@PathVariable String userid) throws MarketplaceException;

    @GetMapping("locations/{locationId}")
    List<Order> getLocationsOrders(@PathVariable String locationId) throws MarketplaceException;

    @PostMapping
    Order addProductToCart(@RequestBody CreateOrder order) throws MarketplaceException;

    @DeleteMapping("{orderNumber}/products/{productSku}")
    void removeProductFromCart(@PathVariable String orderNumber, @PathVariable  String productSku) throws MarketplaceException;

    @PutMapping("increase/{orderNumber}/products/{productSku}")
    void increaseItemQuantity(@PathVariable  String orderNumber, @PathVariable  String productSku) throws MarketplaceException;

    @PutMapping("decrease/{orderNumber}/products/{productSku}")
    void decreaseItemQuantity(@PathVariable  String orderNumber, @PathVariable  String productSku) throws MarketplaceException;

    @PutMapping("status/{orderNumber}")
    void updateStatusOfAnOrder(@PathVariable  String orderNumber) throws MarketplaceException;

    @PutMapping("locations/{sellerEmail}/confirm/{userId}/payment/{paymentMethodId}")
    void confirmOrders(@PathVariable  String userId, @PathVariable  int paymentMethodId, @PathVariable String sellerEmail) throws MarketplaceException;

    @PutMapping("locations/{sellerEmail}/confirm/gast")
    void confirmGastOrders(@RequestBody List<CreateOrder> orders, @PathVariable  String sellerEmail) throws MarketplaceException;

    @PutMapping("cancel/{orderNumber}")
    void cancelOrder(@PathVariable  String orderNumber) throws MarketplaceException;

    @GetMapping("{orderNumber}")
    Order getSingleOrder(@PathVariable String orderNumber) throws MarketplaceException;

}