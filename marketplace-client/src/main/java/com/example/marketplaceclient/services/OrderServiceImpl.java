package com.example.marketplaceclient.services;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.feign.LocationServiceFeignClient;
import com.example.marketplaceclient.feign.OrderServiceFeignClient;
import com.example.marketplaceclient.feign.ProductServiceFeignClient;
import com.example.marketplaceclient.feign.StockServiceFeignClient;
import com.example.marketplaceclient.model.*;
import com.example.marketplaceclient.model.dto.CartItemDto;
import com.example.marketplaceclient.model.dto.CreateOrderDto;
import com.example.marketplaceclient.model.dto.CreateUnregisteredUserOrder;
import com.example.marketplaceclient.model.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderServiceFeignClient feignClient;

    private final ProductServiceFeignClient productServiceFeignClient;

    private final StockServiceFeignClient stockServiceFeignClient;

    private final LocationServiceFeignClient locationServiceFeignClient;

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orderList;
        List<OrderDto> orderDtoList = new ArrayList<>();

        try {
            orderList = this.feignClient.getAllOrders();
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        orderList.forEach(order -> orderDtoList.add(this.toOrderDto(order)));
        return orderDtoList;
    }

    @Override
    public List<OrderDto> getNonConfirmedOrders(String userId) {
        List<Order> orderList;
        List<OrderDto> orderDtoList = new ArrayList<>();

        try {
            orderList = this.feignClient.getNonConfirmedOrders(userId);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        orderList.forEach(order -> orderDtoList.add(this.toOrderDto(order)));
        return orderDtoList;
    }

    @Override
    public List<OrderDto> getConfirmedOrdersByLocationId(String locationId) {
        List<Order> orderList;
        List<OrderDto> orderDtoList = new ArrayList<>();

        try {
            orderList = this.feignClient.getConfirmedOrdersByLocationId(locationId);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        orderList.forEach(order -> orderDtoList.add(this.toOrderDto(order)));
        return orderDtoList;
    }

    @Override
    public List<OrderDto> getConfirmedOrderByLocationIdAndStatus(String locationId, String status) {
        List<Order> orderList;
        List<OrderDto> orderDtoList = new ArrayList<>();

        try {
            orderList = this.feignClient.getConfirmedOrdersByLocationIdAndStatus(locationId, status);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        orderList.forEach(order -> orderDtoList.add(this.toOrderDto(order)));
        return orderDtoList;
    }

    @Override
    public void sendCommentToSeller(UserInformation userInformation) {

        try {
            this.feignClient.sendCommandToSeller(userInformation);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public OrderDto addProductToCartItems(CreateOrderDto orderDto) {
        Order order;

        try {
            order = this.feignClient.addProductToCarts(orderDto);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        return this.toOrderDto(order);
    }

    @Override
    public void updateOrder(String id) {
        Order order;

        try {
            order = this.feignClient.updateStatus(id);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        if (order.getStatus().equalsIgnoreCase("Delivre")) {
            for (CartItem item : order.getCartItems()) {
                try {
                    this.stockServiceFeignClient.getProductAndUpdateQuantity(item.getProductSku(), item.getQuantity());
                } catch (MarketplaceException e) {
                    throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
                }
            }
        }


    }

    @Override
    public void cancelOrder(String id) {
        try {
            this.feignClient.cancelOrder(id);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public List<OrderDto> getConfirmedOrders(String userId) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Order> orders;

        try {
            orders = this.feignClient.getConfirmedOrders(userId);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        orders.forEach(order -> orderDtoList.add(this.toOrderDto(order)));

        return orderDtoList;
    }

    @Override
    public void increaseQuantity(String id, String sku) {
        try {
            this.feignClient.increaseQuantity(id, sku);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public void decreaseQuantity(String id, String sku) {
        try {
            this.feignClient.decreaseQuantity(id,sku);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public void deleteItemFromOrder(String id, String sku) {
        try {
            this.feignClient.deleteItemFromOrder(id, sku);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public OrderDto sendUnregisteredUserOrder(CreateUnregisteredUserOrder order) {
        Order createdOrder;

        try {
            createdOrder =  this.feignClient.sendOrderFromUnregisteredUsers(order);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        return this.toOrderDto(createdOrder);
    }

    private OrderDto toOrderDto(Order order) {
        List<CartItemDto> cartItemDtos = new ArrayList<>();
        Location location = new Location();

        for (CartItem item : order.getCartItems()) {
            try {

                location = this.locationServiceFeignClient.getALocation(order.getLocationId());
                Product product = this.productServiceFeignClient.getProductBySku(item.getProductSku());

                CartItemDto cartItemDto = new CartItemDto(
                        product.getProductSku(),
                        product.getName(),
                        product.getImageUrl(),
                        item.getQuantity(),
                        item.getPrice(),
                        item.getInitialPrice()
                );

                cartItemDtos.add(cartItemDto);
            } catch (MarketplaceException e) {
                throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
            }
        }

        return new OrderDto(
                order.get_id(),
                order.getStatus(),
                order.isConfirmed(),
                order.getPayMentMode(),
                order.getCreatedAt(),
                order.getUpdatedAt(),
                order.getUserInformation(),
                cartItemDtos,
                location
        );
    }
}
