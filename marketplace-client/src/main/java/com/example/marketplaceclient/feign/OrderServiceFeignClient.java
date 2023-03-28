package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.DeliveryDate;
import com.example.marketplaceclient.model.Order;
import com.example.marketplaceclient.model.SellReport;
import com.example.marketplaceclient.model.UserInformation;
import com.example.marketplaceclient.model.dto.CreateOrderDto;
import com.example.marketplaceclient.model.dto.CreateUnregisteredUserOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.error.Mark;

import java.util.List;

@FeignClient(url = "http://houlala_marketplace:3000/api/orders", name = "orders")
public interface OrderServiceFeignClient {

    @GetMapping("")
    List<Order> getAllOrders() throws MarketplaceException;

    @GetMapping("/carts")
    List<Order> getNonConfirmedOrders(@RequestParam(value = "userId") String userId) throws MarketplaceException;

    @GetMapping("/confirmed")
    List<Order> getConfirmedOrders(@RequestParam(value = "userId") String userId) throws MarketplaceException;

    @GetMapping("/confirmed/location/{id}")
    List<Order> getConfirmedOrdersByLocationId(@PathVariable(value = "id") String locationId) throws MarketplaceException;

    @GetMapping("/confirmed/location/{id}/status/{status}")
    List<Order> getConfirmedOrdersByLocationIdAndStatus(@PathVariable(value = "id") String locationId, @PathVariable("status") String status) throws MarketplaceException;

    @PutMapping("/command")
    void sendCommandToSeller(@RequestBody UserInformation userInformation) throws MarketplaceException;

    @PostMapping("")
    Order addProductToCarts(@RequestBody CreateOrderDto newOrder) throws MarketplaceException;

    @GetMapping("/location/{id}")
    List<Order> getOrdersByLocationId(@PathVariable("id") String locationId) throws MarketplaceException;

    @PutMapping("/status/{id}")
    Order updateStatus(@PathVariable(name = "id") String id) throws MarketplaceException;

    @PutMapping("/cancel/{id}")
    Order cancelOrder(@PathVariable(name = "id") String id) throws MarketplaceException;

    @PutMapping("/cartItems/increase/{id}/sku/{sku}")
    void increaseQuantity(@PathVariable(value = "id") String id, @PathVariable(value = "sku") String sku) throws MarketplaceException;

    @PutMapping("/cartItems/decrease/{id}/sku/{sku}")
    void decreaseQuantity(@PathVariable(value = "id") String id, @PathVariable(value = "sku") String sku) throws MarketplaceException;

    @DeleteMapping("/cartItems/{id}/sku/{sku}")
    void deleteItemFromOrder(@PathVariable(value = "id") String id, @PathVariable(value = "sku") String sku) throws MarketplaceException;

    @PostMapping("/unregistereds")
    Order sendOrderFromUnregisteredUsers(@RequestBody CreateUnregisteredUserOrder order) throws MarketplaceException;

    @PutMapping("/deliveryDate/{id}")
    void updateDeliveryDate(@PathVariable(value = "id") String id, @RequestBody DeliveryDate date) throws MarketplaceException;

    @GetMapping("/top/{locationId}")
    List<SellReport> getTopOrders(@PathVariable(value = "locationId") String locationId) throws MarketplaceException;

    @GetMapping("/count/today/location/{locationId}")
    long getTodayOrderCountByLocationId(@PathVariable(value = "locationId") int locationId) throws MarketplaceException;

    @GetMapping("/count/today/location/{locationId}/status/{status}")
    long getTodayOrderCountByLocationIdAndStatus(@PathVariable(value = "locationId") int locationId, @PathVariable(value = "status") String status) throws MarketplaceException;

    @GetMapping("/count/location/{locationId}")
    long getOrderCountByLocationId(@PathVariable(value = "locationId") int locationId) throws MarketplaceException;

    @GetMapping("/count/location/{locationId}/status/{status}")
    long getOrderCountByLocationIdAndStatus(@PathVariable(value = "locationId") int locationId, @PathVariable(value = "status") String status) throws MarketplaceException;

    @GetMapping("{id}")
    Order getSingleOrder(@PathVariable(value = "id") String id) throws MarketplaceException;

    @GetMapping("/location/{id}")
    List<Order> getOrderListByLocationId(@PathVariable(value = "id") String locationId, @RequestParam(value = "size", required = false) int size) throws MarketplaceException;

}
