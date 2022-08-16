package com.example.marketplaceclient.controller;

import com.example.marketplaceclient.model.UserInformation;
import com.example.marketplaceclient.model.dto.CreateOrderDto;
import com.example.marketplaceclient.model.dto.CreateUnregisteredUserOrder;
import com.example.marketplaceclient.model.dto.OrderDto;
import com.example.marketplaceclient.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/carts")
    public List<OrderDto> getCartItems(@RequestParam String userId) {
        return this.orderService.getNonConfirmedOrders(userId);
    }

    @GetMapping("/location/{id}")
    public List<OrderDto> getConfirmedOrdersByLocationId(@PathVariable("id") String locationId) {
        return this.orderService.getConfirmedOrdersByLocationId(locationId);
    }

    @GetMapping("/location/{id}/status/{status}")
    public List<OrderDto> getConfirmedOrdersByLocationIdAndStatus(@PathVariable("id") String locationId, @PathVariable String status) {
        return this.orderService.getConfirmedOrderByLocationIdAndStatus(locationId, status);
    }

    @PutMapping("/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendOrderToSeller(@RequestBody UserInformation userInformation) {
        this.orderService.sendCommentToSeller(userInformation);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
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


    @GetMapping("/confirmed")
    public List<OrderDto> getConfirmedByUserId(@RequestParam String userId) {
        return this.orderService.getConfirmedOrders(userId);
    }

    @PutMapping("/cartItems/increase/{id}/sku/{sku}")
    public void increaseQuantity(@PathVariable String id, @PathVariable String sku) {
        this.orderService.increaseQuantity(id, sku);
    }

    @PutMapping("/cartItems/decrease/{id}/sku/{sku}")
    public void decreaseQuantity(@PathVariable String id, @PathVariable String sku) {
        this.orderService.decreaseQuantity(id, sku);
    }

    @DeleteMapping("/cartItems/{id}/sku/{sku}")
    public void deleteItemFromOrder(@PathVariable String id, @PathVariable String sku) {
        this.orderService.deleteItemFromOrder(id, sku);
    }

    @PostMapping("/unregistereds")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto sendUnregisteredUserOrder(@RequestBody CreateUnregisteredUserOrder order){
        return this.orderService.sendUnregisteredUserOrder(order);
    }

    @GetMapping("/total/{locationId}")
    public long getTotalOrderCount(@PathVariable String locationId){
        return this.orderService.getOrderTotalCount(locationId);
    }

    @GetMapping("/sold/{locationId}")
    public long getOrderSoldCount(@PathVariable String locationId){
        return this.orderService.getOrderSoldCount(locationId);
    }

    @GetMapping("/canceled/{locationId}")
    public long getCanceledOrderCount(@PathVariable String locationId){
        return this.orderService.getOrderCanceledCount(locationId);
    }

    @GetMapping("/all/{locationId}")
    public List<OrderDto> getAllOrdersFromLocationId(@PathVariable String locationId){
        return this.orderService.getAllOrdersByLocationId(locationId);
    }
}
