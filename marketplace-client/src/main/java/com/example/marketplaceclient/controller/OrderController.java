package com.example.marketplaceclient.controller;

import com.example.marketplaceclient.model.DeliveryDate;
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


    @GetMapping("/carts")
    public List<OrderDto> getCartItems(@RequestParam(value = "userId") String userId) {
        return this.orderService.getNonConfirmedOrders(userId);
    }

    @GetMapping("/location/{id}")
    public List<OrderDto> getConfirmedOrdersByLocationId(@PathVariable("id") String locationId) {
        return this.orderService.getConfirmedOrdersByLocationId(locationId);
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
    public void updateStatus(@PathVariable(value = "id") String id) {
        this.orderService.updateOrder(id);
    }

    @PutMapping("/cancel/{id}")
    public void cancelOrder(@PathVariable(value = "id") String id) {
        this.orderService.cancelOrder(id);
    }


    @GetMapping("/confirmed")
    public List<OrderDto> getConfirmedByUserId(@RequestParam String userId) {
        return this.orderService.getConfirmedOrders(userId);
    }

    @PutMapping("/cartItems/increase/{id}/sku/{sku}")
    public void increaseQuantity(@PathVariable(value = "id") String id, @PathVariable(value = "sku") String sku) {
        this.orderService.increaseQuantity(id, sku);
    }

    @PutMapping("/cartItems/decrease/{id}/sku/{sku}")
    public void decreaseQuantity(@PathVariable(value = "id") String id, @PathVariable(value = "sku") String sku) {
        this.orderService.decreaseQuantity(id, sku);
    }

    @DeleteMapping("/cartItems/{id}/sku/{sku}")
    public void deleteItemFromOrder(@PathVariable(value = "id") String id, @PathVariable(value = "sku") String sku) {
        this.orderService.deleteItemFromOrder(id, sku);
    }

    @PostMapping("/unregistereds")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto sendUnregisteredUserOrder(@RequestBody CreateUnregisteredUserOrder order) {
        return this.orderService.sendUnregisteredUserOrder(order);
    }

    @GetMapping("/location/filter")
    public List<OrderDto> filterOrdersByLocationId(@RequestParam(value = "locationId", required = false) String locationId,
                                                   @RequestParam(value = "status", required = false) String status,
                                                   @RequestParam(value = "size", required = false) int size
    ) {
        return this.orderService.getOrdersByLocationId(locationId, status, size);
    }

    @GetMapping("/total/{locationId}")
    public long getTotalOrderCount(@PathVariable(value = "locationId") String locationId) {
        return this.orderService.getOrderTotalCount(locationId);
    }

    @GetMapping("/sold/{locationId}")
    public long getOrderSoldCount(@PathVariable(value = "locationId") String locationId) {
        return this.orderService.getOrderSoldCount(locationId);
    }

    @GetMapping("/canceled/{locationId}")
    public long getCanceledOrderCount(@PathVariable(value = "locationId") String locationId) {
        return this.orderService.getOrderCanceledCount(locationId);
    }

    @PutMapping("/deliveryDate/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDeliveryDate(@PathVariable(value = "id") String id, @RequestBody DeliveryDate newDate) {
        this.orderService.updateDeliveryDate(id, newDate);
    }
}