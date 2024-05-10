package com.houlala.marketplace.orders;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.houlala.marketplace.location.Location;
import com.houlala.marketplace.location.LocationFeign;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService{

    private final OrderFeign orderFeign; 

    private final LocationFeign locationFeign; 

    private final OrdersMapper ordersMapper;

    @Override
    public List<OrderDto> getUsersOrders(String userId) {
        List<OrderDto> orderDtos = new ArrayList<>(); 
        List<Order> orders = orderFeign.getUsersOrders(userId); 
        for(Order order: orders){
            Location location = locationFeign.getALocation(order.getLocationUniqueId()); 
            orderDtos.add(ordersMapper.toOrderDto(order, location));
        }
        return orderDtos; 
    }

    @Override
    public List<OrderDto> getLocationsOrders(String locationId) {
        List<OrderDto> orderDtos = new ArrayList<>(); 
        List<Order> orders = orderFeign.getLocationsOrders(locationId);
        for( Order order : orders){
            Location location = locationFeign.getALocation(order.getLocationUniqueId()); 
            orderDtos.add(ordersMapper.toOrderDto(order, location));
        }
        return orderDtos;
    }

    @Override
    public OrderDto addProductToCart(CreateOrder order) {
        Order createdOrder = orderFeign.addProductToCart(order);
        Location location = locationFeign.getALocation(order.getLocationUniqueId());
        return ordersMapper.toOrderDto(createdOrder, location);
    }

    @Override
    public void removeProductFromCart(String orderNumber, String productSku) {
        orderFeign.removeProductFromCart(orderNumber, productSku);
    }

    @Override
    public void increaseItemQuantity(String orderNumber, String productSku) {
        orderFeign.increaseItemQuantity(orderNumber, productSku);
    }

    @Override
    public void decreaseItemQuantity(String orderNumber, String productSku) {
        orderFeign.decreaseItemQuantity(orderNumber, productSku);
    }

    @Override
    public void updateStatusOfAnOrder(String orderNumber) {
        orderFeign.updateStatusOfAnOrder(orderNumber);
    }

    @Override
    public void confirmOrders(String userId, int paymentMethodId, String sellerEmail) {
        orderFeign.confirmOrders(userId, paymentMethodId, sellerEmail);
    }

    @Override
    public void confirmGastOrders(List<CreateOrder> orders, String sellerEmail) {
        orderFeign.confirmGastOrders(orders, sellerEmail);
    }

    @Override
    public void cancelOrder(String orderNumber) {
       orderFeign.cancelOrder(orderNumber);
    }

    @Override
    public OrderDto getSingleOrder(String ordeNumber) {
        Order order= orderFeign.getSingleOrder(ordeNumber); 
        Location location = locationFeign.getALocation(order.getLocationUniqueId());
        return this.ordersMapper.toOrderDto(order, location); 

    }
    
}
