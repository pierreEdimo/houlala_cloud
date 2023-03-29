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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
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
            log.error(e.getMessage());
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
            log.error(e.getMessage());
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
            log.error(e.getMessage());
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
            log.error(e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public OrderDto addProductToCartItems(CreateOrderDto orderDto) {
        Order order;

        try {
            order = this.feignClient.addProductToCarts(orderDto);
        } catch (MarketplaceException e) {
            log.error(e.getMessage());
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
            log.error(e.getMessage());
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
            log.error(e.getMessage());
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
            log.error(e.getMessage());
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
            log.error(e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public void decreaseQuantity(String id, String sku) {
        try {
            this.feignClient.decreaseQuantity(id, sku);
        } catch (MarketplaceException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public void deleteItemFromOrder(String id, String sku) {
        try {
            this.feignClient.deleteItemFromOrder(id, sku);
        } catch (MarketplaceException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public OrderDto sendUnregisteredUserOrder(CreateUnregisteredUserOrder order) {
        Order createdOrder;

        try {
            createdOrder = this.feignClient.sendOrderFromUnregisteredUsers(order);
        } catch (MarketplaceException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        return this.toOrderDto(createdOrder);
    }

    @Override
    public long getOrderTotalCount(String locationId) {
        try {
            return this.feignClient.getOrderCountByLocationId(locationId);
        } catch (MarketplaceException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public long getOrderSoldCount(String locationId) {
        try {
            return this.feignClient.getOrderCountByLocationIdAndStatus(locationId, "Delivre");
        } catch (MarketplaceException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public long getOrderCanceledCount(String locationId) {
        try {
            return this.feignClient.getOrderCountByLocationIdAndStatus(locationId, "Annule");
        } catch (MarketplaceException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public void updateDeliveryDate(String id, DeliveryDate newDate) {
        try {
            this.feignClient.updateDeliveryDate(id, newDate);
        } catch (MarketplaceException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public List<OrderDto> getOrdersByLocationId(String locationId, String status, int size) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Order> orders;

        try {
            orders = this.feignClient.getAllOrders();

            if (locationId != null) {
                orders = this.feignClient.getOrdersByLocationId(locationId, size);
            }

        } catch (MarketplaceException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        if (status != null) {
            orders = orders.stream()
                    .filter(x -> x.getStatus().equalsIgnoreCase(status))
                    .collect(Collectors.toList());

        }

        orders.forEach(order -> orderDtoList.add(this.toOrderDto(order)));

        return orderDtoList;
    }

    private OrderDto toOrderDto(Order order) {
        List<CartItemDto> cartItemDtos = new ArrayList<>();
        Location location = new Location();

        for (CartItem item : order.getCartItems()) {
            try {

                location = this.locationServiceFeignClient.getALocation(order.getLocationId());
                Product product = this.productServiceFeignClient.getProductBySku(item.getProductSku());

                CartItemDto cartItemDto = new CartItemDto(product.getProductSku(), product.getName(), product.getImageUrl(), item.getQuantity(), item.getPrice(), item.getInitialPrice());

                cartItemDtos.add(cartItemDto);
            } catch (MarketplaceException e) {
                log.error(e.getMessage());
                throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
            }
        }

        return new OrderDto(order.get_id(), order.getStatus(), order.isConfirmed(), order.getPayMentMode(), order.getCreatedAt(), order.getUpdatedAt(), order.getUserInformation(), cartItemDtos, location, order.getDeliveryDate());
    }
}
