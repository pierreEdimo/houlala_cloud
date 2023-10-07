package com.houlala.marketplace.order;

import com.houlala.marketplace.exception.MarketplaceException;
import com.houlala.marketplace.information.UserInformation;
import com.houlala.marketplace.model.Count;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "order", url = "https://marketplace-api.houlala.store/api/orders")
public interface OrderFeign {

    @GetMapping
    List<Order> getAllOrders() throws MarketplaceException;

    @PostMapping("/carts")
    void addProducts(@RequestBody CreateOrder order) throws MarketplaceException;

    @GetMapping("/count/today/locations/{luid}")
    Count getTodayOrderCount(@PathVariable String luid) throws MarketplaceException;

    @GetMapping("/count/today/locations/{luid}/status/{status}")
    Count getTodayOrderCountByLocationAndStatus(@PathVariable String luid, @PathVariable String status) throws MarketplaceException;

    @GetMapping("/carts/users/{userId}")
    List<Order> getCartList(@PathVariable String userId) throws MarketplaceException;

    @GetMapping("/{id}")
    Order getSingleOrder(@PathVariable int id) throws MarketplaceException;

    @GetMapping("/count/locations/{luid}/status/{status}")
    Count getOrdersCountByLocationAndStatus(@PathVariable String luid, @PathVariable String status) throws MarketplaceException;

    @GetMapping("/count/locations/{luid}")
    Count getTotalOrderCount(@PathVariable String luid) throws MarketplaceException;

    @PutMapping("/order")
    void confirmOrder(@RequestBody UserInformation userIn) throws MarketplaceException;

    @GetMapping("/confirmed/users/{id}")
    List<Order> getUserConfirmedOrders(@PathVariable(value = "id") String userId) throws MarketplaceException;

    @GetMapping("/confirmed/locations/{luid}/status/{status}")
    List<Order> getConfirmedOrdersByLocationIdAndStatus(@PathVariable String luid, @PathVariable  String status) throws MarketplaceException;

    @GetMapping("/confirmed/locations/{luid}")
    List<Order> getConfirmedOrdersByLocationId(@PathVariable String luid) throws MarketplaceException;

    @GetMapping("/confirmed/locations/{luid}/limit/{size}")
    List<Order> getConfirmedOrdersByLocationIdAndLimit(@PathVariable String luid, @PathVariable int size) throws MarketplaceException;

    @GetMapping("/confirmed/today/locations/{luid}")
    List<Order> getTodayOrderByLocationId(@PathVariable String luid) throws MarketplaceException;

    @PutMapping("/status/{id}")
    Order updateStatusOfOrder(@PathVariable int id) throws MarketplaceException;

    @PutMapping("/cancel/{id}")
    void cancelOrder(@PathVariable int id) throws MarketplaceException;

    @PutMapping("/cartItems/increase/{id}/sku/{sku}")
    void increaseItemQuantity(@PathVariable int id,  @PathVariable String sku) throws MarketplaceException;

    @PutMapping("/cartItems/decrease/{id}/sku/{sku}")
    void decreaseItemQuantity(@PathVariable int id, @PathVariable String sku) throws MarketplaceException;

    @PostMapping("/unregistered")
    void confirmUnregisteredUsersOrders(@RequestBody CreateUnregisteredOrder order) throws MarketplaceException;

    @DeleteMapping("/cartItems/{id}/sku/{sku}")
    void removeItemFromOrder(@PathVariable int id, @PathVariable String sku) throws MarketplaceException;

    @DeleteMapping("/{id}")
    void deleteOrder(@PathVariable int id) throws MarketplaceException;

    @PutMapping("/deliveryDate/{id}")
    void updateDeliveryDate(@PathVariable int id, @RequestBody DeliveryDate date) throws MarketplaceException;

    @GetMapping("/top/{luid}")
    List<SellReport> getTopOrders(@PathVariable String luid) throws MarketplaceException;
}
