package com.houlala.marketplace.order;

import com.houlala.marketplace.information.UserInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getAllOrders(){
        return this.orderService.getAllOrders();
    }

    @PostMapping
    public void addProducts(@RequestBody CreateOrder order){
        this.orderService.addProducts(order);
    }

    @GetMapping("/count/today/locations/{luid}")
    public int getTodayOrderCount(@PathVariable String luid){
        return this.orderService.getTodayOrderCount(luid);
    }

    @GetMapping("/count/today/locations/{luid}/status/{status}")
    public int getTodayOrderCountByLocationAndStatus(@PathVariable String luid, @PathVariable String status){
        return this.orderService.getTodayOrderCountByLocationAndStatus(luid, status);
    }

    @GetMapping("/carts/{userId}")
    public List<OrderDto> getCartList(@PathVariable String userId){
        return this.orderService.getCartList(userId);
    }

    @GetMapping("/{id}")
    public OrderDto getSingleOrder(@PathVariable int id){
        return this.orderService.getSingleOrder(id);
    }

    @GetMapping("/count/locations/{luid}/status/{status}")
    public int getOrdersCountByLocationAndStatus(@PathVariable String luid, @PathVariable String status){
        return this.orderService.getOrdersCountByLocationAndStatus(luid, status);
    }

    @GetMapping("/count/locations/total/{luid}")
    public int getTotalOrderCount(@PathVariable String luid){
        return this.orderService.getTotalOrderCount(luid);
    }

    @GetMapping("/count/locations/canceled/{luid}")
    public int getCanceledOrderCount(@PathVariable String luid){
        return this.orderService.getCanceledOrdersCount(luid);
    }

    @GetMapping("/count/locations/sold/{luid}")
    public int getSoldOrderCount(@PathVariable String luid){
        return this.orderService.getOrderSoldCount(luid);
    }

    @PutMapping("/confirm")
    public void confirmOrder(@RequestBody UserInformation userInformation){
        this.orderService.confirmOrder(userInformation);
    }

    @GetMapping("/confirmed/locations/{luid}/status/{status}")
    public List<OrderDto> getConfirmedOrdersByLocationIdAndStatus(@PathVariable String luid, @PathVariable String status){
        return this.orderService.getConfirmedOrdersByLocationIdAndStatus(luid, status);
    }

    @GetMapping("/confirmed/locations/{luid}")
    public List<OrderDto> getConfirmedOrdersByLocationId(@PathVariable String luid){
        return this.orderService.getConfirmedOrdersByLocationId(luid);
    }

    @GetMapping("/confirmed/locations/{luid}/limit/{size}")
    public List<OrderDto> getConfirmedOrdersByLocationAndLimit(@PathVariable String luid, @PathVariable int size){
        return this.orderService.getConfirmedOrdersByLocationIdAndLimit(luid, size);
    }

    @GetMapping("/today/locations/{luid}")
    public List<OrderDto> getTodayOrderByLocationId(@PathVariable String luid){
        return this.orderService.getTodayOrderByLocationId(luid);
    }

    @PutMapping("/status/{id}")
    public void updateStatusOfOrder(@PathVariable int id){
        this.orderService.updateStatusOfOrder(id);
    }

    @PutMapping("/cancel/{id}")
    public void cancelOrder(@PathVariable int id){
        this.orderService.cancelOrder(id);
    }

    @PutMapping("/cartItems/increase/{id}/sku/{sku}")
    public void increaseItemQuantity(@PathVariable int id, @PathVariable String sku){
        this.orderService.increaseItemQuantity(id, sku);
    }

    @PutMapping("/cartItems/decrease/{id}/sku/{sku}")
    public void decreaseItemQuantity(@PathVariable int id, @PathVariable String sku){
        this.orderService.decreaseItemQuantity(id, sku);
    }

    @PutMapping("/cartItems/{id}/sku/{sku}")
    public void deleteItemFromCart(@PathVariable int id, @PathVariable String sku){
        this.orderService.removeItemFromOrder(id, sku);
    }

    @PutMapping("/unregistered")
    public void confirmUnregisteredUsersOrders(@RequestBody CreateUnregisteredOrder order){
        this.orderService.confirmUnregisteredUsersOrders(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable int id){
        this.orderService.deleteOrder(id);
    }

    @PutMapping("/delivery/{id}")
    public void updateDeliveryDate(@PathVariable int id, @RequestBody DeliveryDate date){
        this.orderService.updateDeliveryDate(id, date);
    }

    @GetMapping("/confirmed/users/{userId}")
    public List<OrderDto> confirmedUsersOrders(@PathVariable String userId){
        return this.orderService.getConfirmedUsersOrders(userId);
    }
}
