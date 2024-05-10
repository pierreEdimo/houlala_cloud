package com.houlala.marketplace.orders;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrderService orderService;

    @GetMapping("/users/{userId}")
    public List<OrderDto> getUsersOrders(@PathVariable String userId) {
        return orderService.getUsersOrders(userId);
    }

    @GetMapping("/locations/{locationId}")
    public List<OrderDto> getLocationsOrders(@PathVariable String locationId) {
        return orderService.getLocationsOrders(locationId);
    }

    @PutMapping("/carts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public OrderDto addProductToCart(@RequestBody CreateOrder order) {
        return orderService.addProductToCart(order);
    }

    @DeleteMapping("/carts/{orderNumber}/products/{productSku}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeProductFromCart(@PathVariable String orderNumber, @PathVariable String productSku) {
        orderService.removeProductFromCart(orderNumber, productSku);
    }

    @PutMapping("/carts/increase/{orderNumber}/products/{productSku}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void increaseItemQuantity(@PathVariable String orderNumber, @PathVariable String productSku) {
        orderService.increaseItemQuantity(orderNumber, productSku);
    }

    @PutMapping("/carts/decrease/{orderNumber}/products/{productSku}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void decreaseItemQuantity(@PathVariable String orderNumber, @PathVariable String productSku) {
        orderService.decreaseItemQuantity(orderNumber, productSku);
    }

    @PutMapping("/status/{orderNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatusOfAnOrder(@PathVariable String orderNumber) {
        orderService.updateStatusOfAnOrder(orderNumber);
    }

    @PutMapping("/users/{userId}/payment/{paymentMethodId}/locations/{sellerEmail}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmOrders(@PathVariable String userId, int paymentMethodId, String sellerEmail) {
        orderService.confirmOrders(userId, paymentMethodId, sellerEmail);
    }

    @PutMapping("/gast/locations/{sellerEmail}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmGastOrders(@RequestBody List<CreateOrder> orders, String sellerEmail) {
        orderService.confirmGastOrders(orders, sellerEmail);
    }

    @PutMapping("/cancel/{orderNumber}")
    public void cancelOrder(@PathVariable String orderNumber) {
        orderService.cancelOrder(orderNumber);
    }

    @GetMapping("/{orderNumber}")
    public OrderDto getSingleOrder(@PathVariable String orderNumber) {
        return orderService.getSingleOrder(orderNumber);
    }
}
