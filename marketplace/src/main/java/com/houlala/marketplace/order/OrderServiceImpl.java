package com.houlala.marketplace.order;

import com.houlala.marketplace.information.UserInformation;
import com.houlala.marketplace.location.Location;
import com.houlala.marketplace.location.LocationFeign;
import com.houlala.marketplace.stock.StockFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderFeign orderFeign;

    private final StockFeignClient stockFeignClient;

    private final LocationFeign locationFeign;

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = this.orderFeign.getAllOrders();
        List<OrderDto> orderDtos = new ArrayList<>();
        orders.forEach(order -> orderDtos.add(this.toOrderDto(order)));
        return orderDtos;
    }

    @Override
    public void addProducts(CreateOrder order) {
        this.orderFeign.addProducts(order);
    }

    @Override
    public int getTodayOrderCount(String luid) {
        return this.orderFeign.getTodayOrderCount(luid).getValue();
    }

    @Override
    public int getTodayOrderCountByLocationAndStatus(String luid, String status) {
        return this.orderFeign.getTodayOrderCountByLocationAndStatus(luid, status).getValue();
    }

    @Override
    public List<OrderDto> getCartList(String userId) {
        List<Order> orders = this.orderFeign.getCartList(userId);
        List<OrderDto> orderDtos = new ArrayList<>();
        orders.forEach(order -> orderDtos.add(this.toOrderDto(order)));
        return orderDtos;


    }

    @Override
    public OrderDto getSingleOrder(int id) {
        Order order = this.orderFeign.getSingleOrder(id);
        return this.toOrderDto(order);
    }

    @Override
    public int getOrdersCountByLocationAndStatus(String luid, String status) {
        return this.orderFeign.getOrdersCountByLocationAndStatus(luid, status).getValue();
    }

    @Override
    public int getTotalOrderCount(String luid) {
        return this.orderFeign.getTotalOrderCount(luid).getValue();
    }

    @Override
    public int getCanceledOrdersCount(String luid) {
        return this.orderFeign.getOrdersCountByLocationAndStatus(luid, "Annule").getValue();
    }

    @Override
    public int getOrderSoldCount(String luid) {
        return this.orderFeign.getOrdersCountByLocationAndStatus(luid, "Delivre").getValue();
    }

    @Override
    public void confirmOrder(UserInformation userInfo) {
        this.orderFeign.confirmOrder(userInfo);
    }

    @Override
    public List<OrderDto> getConfirmedOrdersByLocationIdAndStatus(String luid, String status) {
        List<Order> orderList = this.orderFeign.getConfirmedOrdersByLocationIdAndStatus(luid, status);
        List<OrderDto> orderDtoList = new ArrayList<>();
        orderList.forEach(order -> orderDtoList.add(this.toOrderDto(order)));
        return orderDtoList;

    }

    @Override
    public List<OrderDto> getConfirmedOrdersByLocationId(String luid) {
        List<Order> orderList = this.orderFeign.getConfirmedOrdersByLocationId(luid);
        List<OrderDto> orderDtos = new ArrayList<>();
        orderList.forEach(order -> orderDtos.add(this.toOrderDto(order)));
        return orderDtos;

    }

    @Override
    public List<OrderDto> getConfirmedUsersOrders(String userId) {
        List<Order> orderList = this.orderFeign.getUserConfirmedOrders(userId);
        List<OrderDto> orderDtos = new ArrayList<>();
        orderList.forEach(order -> orderDtos.add(this.toOrderDto(order)));
        return orderDtos;
    }

    @Override
    public List<OrderDto> getConfirmedOrdersByLocationIdAndLimit(String luid, int size) {
        List<Order> orderList = this.orderFeign.getConfirmedOrdersByLocationIdAndLimit(luid, size);
        List<OrderDto> orderDtos = new ArrayList<>();
        orderList.forEach(order -> orderDtos.add(this.toOrderDto(order)));
        return orderDtos;
    }

    @Override
    public List<OrderDto> getTodayOrderByLocationId(String luid) {
        List<Order> orderList =  this.orderFeign.getTodayOrderByLocationId(luid);
        List<OrderDto> orderDtos = new ArrayList<>();
        orderList.forEach(order -> orderDtos.add(this.toOrderDto(order)));
        return orderDtos;
    }

    @Override
    public void updateStatusOfOrder(int id) {
        Order order = this.orderFeign.updateStatusOfOrder(id);

        if(order.getStatus().equalsIgnoreCase("Delivre")){
            for(CartItem item: order.getCartItems()){
                this.stockFeignClient.getProductAndUpdateQuantity(item.getProductSku(), item.getQuantity());
            }
        }
    }

    @Override
    public void cancelOrder(int id) {
        this.orderFeign.cancelOrder(id);
    }

    @Override
    public void increaseItemQuantity(int id, String sku) {
        this.orderFeign.increaseItemQuantity(id, sku);
    }

    @Override
    public void decreaseItemQuantity(int id, String sku) {
        this.orderFeign.decreaseItemQuantity(id, sku);
    }

    @Override
    public void confirmUnregisteredUsersOrders(CreateUnregisteredOrder order) {
        this.orderFeign.confirmUnregisteredUsersOrders(order);
    }

    @Override
    public void removeItemFromOrder(int id, String sku) {
        this.orderFeign.removeItemFromOrder(id, sku);
    }

    @Override
    public void deleteOrder(int id) {
        this.orderFeign.deleteOrder(id);
    }

    @Override
    public void updateDeliveryDate(int id, DeliveryDate date) {
        this.orderFeign.updateDeliveryDate(id, date);
    }

    @Override
    public List<SellReport> getTopOrders(String luid) {
        return this.orderFeign.getTopOrders(luid);
    }

    private OrderDto toOrderDto(Order userOrder){
        Location location = this.locationFeign.getALocation(userOrder.getLocationUniqueId());
        return  new OrderDto(
                userOrder.getId(),
                userOrder.getUserInformation(),
                location,
                userOrder.getDeliveryDate(),
                userOrder.getStatus(),
                userOrder.isConfirmed(),
                userOrder.getPayMentMethode(),
                userOrder.getCreatedAt(),
                userOrder.getUpdatedAt(),
                userOrder.getCartItems(),
                userOrder.getTotalQuantity(),
                userOrder.getTotalPrice()
        );
    }
}
