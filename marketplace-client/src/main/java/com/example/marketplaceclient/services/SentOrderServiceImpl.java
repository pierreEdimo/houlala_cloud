package com.example.marketplaceclient.services;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.feign.OrderServiceFeignClient;
import com.example.marketplaceclient.feign.StockServiceFeignClient;
import com.example.marketplaceclient.model.CartItem;
import com.example.marketplaceclient.model.Order;
import com.example.marketplaceclient.model.dto.CreateProductCartDto;
import com.example.marketplaceclient.model.dto.OrderDto;
import com.example.marketplaceclient.model.dto.UserCart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SentOrderServiceImpl implements SentOrderService {

    private final OrderServiceFeignClient feignClient;

    private final StockServiceFeignClient stockServiceFeignClient;

    @Override
    public List<OrderDto> getOrdersByLocationId(String locationId) {
        List<Order> orderList;
        List<OrderDto> orderDtos = new ArrayList<>();

        try {
            orderList = this.feignClient.fetchOrderFromLocationId(locationId);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        orderList.forEach(order -> orderDtos.add(this.orderToDto(order)));
        return orderDtos;
    }

    @Override
    public Order addProductToCart(CreateProductCartDto newCart) {
        Order order;

        try {
            order = this.feignClient.addProductToCart(newCart);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        return order;
    }

    @Override
    public UserCart getTheCartByUserEmail(String email) {
        int totalPrice = 0;
        int totalQuantity = 0;

        List<CartItem> cartItemList;

        try {
            cartItemList = this.feignClient.getCartsByEmail(email);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        for (CartItem item : cartItemList) {
            totalPrice += item.getTotalPrice();
            totalQuantity += item.getTotalQuantity();
        }

        return new UserCart(
                cartItemList,
                totalQuantity,
                totalPrice
        );
    }

//    public void getSentOrderAndUpdateQuantity(String id) {
//
//        SentOrder order;
//
//        try {
//            order = this.feignClient.changeOrderStatus(id);
//        } catch (MarketplaceException e) {
//            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
//        }
//
//        if(order.getStatus().equals("Delivre")){
//            List<CartItemDto> items = order.getCartItems();
//
//            for(CartItemDto item : items){
//                try {
//                    this.stockServiceFeignClient.getProductAndUpdateQuantity(item.getProduct().getProductSku(), item.getQuantity());
//                } catch (MarketplaceException e) {
//                    throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
//                }
//            }
//        }
//    }

    private OrderDto orderToDto(Order order) {

        Map<String, CartItem> cartItems = order.getCartItems()
                .stream().collect(Collectors.toMap(CartItem::getProductSku, Function.identity(), (a, b) -> new CartItem(
                        a.getName(),
                        a.getDescription(),
                        a.getImageUrl(),
                        a.getTotalPrice() + b.getTotalPrice(),
                        a.getTotalQuantity() + b.getTotalQuantity(),
                        a.getProductSku()
                )));

        return new OrderDto(
                order.getLocationId(),
                order.getPaymentMode(),
                order.getTotalPrice(),
                order.getTotalQuantity(),
                cartItems,
                order.getUserInformation()
        );

    }
}
