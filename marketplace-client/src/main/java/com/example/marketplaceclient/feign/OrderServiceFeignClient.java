package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.*;
import com.example.marketplaceclient.model.dto.CreateOrderDto;
import com.example.marketplaceclient.model.dto.CreateUnregisteredUserOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.error.Mark;

import java.util.List;

@FeignClient(url = "http://houlala_marketplace:3000/api/orders", name = "orders")
public interface OrderServiceFeignClient {

    @GetMapping()
    List<Order> getAllOrders() throws MarketplaceException;

    @PostMapping()
    Order addProducts(@RequestBody() CreateOrderDto orderDto) throws MarketplaceException;

    @GetMapping("/count/today/locations/{luid}")
    Count getTodayOrderCount(@PathVariable(value = "luid") String luid) throws MarketplaceException;

    @GetMapping("/count/today/locations/{luid}/status/{status}")
    Count getTodayOrderCountByLocationIdAndStatus(@PathVariable(value = "luid") String luid, @PathVariable(value = "status") String status) throws MarketplaceException;

    @GetMapping("/carts/users/{id}")
    List<Order> getCartList(@PathVariable(value = "id") String userId) throws MarketplaceException;

    @GetMapping("{id}")
    Order getSingleOrder(@PathVariable(value = "id") int id) throws MarketplaceException;

    @GetMapping("/count/locations/{luid}/status/{status}")
    Count getOrdersCountByLocationIdAndStatus(@PathVariable(value = "luid") String luid, @PathVariable(value = "status") String status) throws MarketplaceException;

    @GetMapping("/count/locations/{luid}")
    Count getTotalOrderCount(@PathVariable(value = "luid") String luid) throws MarketplaceException;

    @GetMapping("/top/{luid}")
    List<SellReport> getTopOrders(@PathVariable(value = "luid") String luid) throws MarketplaceException;

    @PutMapping("/order")
    void confirmOrder(@RequestBody() UserInformation userInfo) throws MarketplaceException;

    @GetMapping("/confirmed/users/{id}")
    List<Order> getUserOrders(@PathVariable(value = "id") String userId) throws MarketplaceException;

    @GetMapping("/confirmed/locations/{luid}/status/{status}")
    List<Order> getConfirmedOrdersByLocationIdAndStatus(@PathVariable(value = "luid") String luid, @PathVariable(value = "status") String status) throws MarketplaceException;

    @GetMapping("/locations/{luid}")
    List<Order> getOrdersByLocationId(@PathVariable(value = "luid") String luid, @RequestParam(value = "size") int size) throws MarketplaceException;

    @PutMapping("/status/{id}")
    Order updateStatusOfOrder(@PathVariable(value = "id") int id) throws MarketplaceException;

    @PutMapping("/cancel/{id}")
    void cancelOrder(@PathVariable(value = "id") int id) throws MarketplaceException;

    @PutMapping("/cartItems/increase/{id}/sku/{sku}")
    void increaseItemQuantity(@PathVariable(value = "id") int id, @PathVariable(value = "sku") String productSku) throws MarketplaceException;

    @PutMapping("/cartItems/decrease/{id}/sku/{sku}")
    void decreaseItemQuantity(@PathVariable(value = "id") int id, @PathVariable(value = "sku") String productSku) throws MarketplaceException;

    @PutMapping("/unregistereds")
    Order confirmUnregisteredUsersOrders(@RequestBody() CreateUnregisteredUserOrder order) throws MarketplaceException;

    @DeleteMapping("/cartItems/{id}/sku/{sku}")
    void removeItemFromOrder(@PathVariable(value = "id") int id, @PathVariable(value = "sku") String sku) throws MarketplaceException;

    @DeleteMapping("{id}")
    void deleteOrder(@PathVariable(value = "id") int id) throws MarketplaceException;

    @GetMapping("/confirmed/locations/{luid}")
    List<Order> getOrdersByLocationId(@PathVariable(value = "luid") String luid) throws MarketplaceException;

    void updateDeliveryDate(@PathVariable(value = "id") int id, @RequestBody() DeliveryDate date) throws MarketplaceException;
}
