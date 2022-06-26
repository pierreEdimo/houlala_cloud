package com.example.marketplaceclient.services;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.feign.SentOrderServiceFeignClient;
import com.example.marketplaceclient.feign.StockServiceFeignClient;
import com.example.marketplaceclient.model.CartItemResponse;
import com.example.marketplaceclient.model.SentOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SentOrderServiceImpl implements SentOrderService {

    private final SentOrderServiceFeignClient feignClient;

    private final StockServiceFeignClient stockServiceFeignClient;


    @Override
    public void getSentOrderAndUpdateQuantity(String id) {

        SentOrder order;

        try {
            order = this.feignClient.changeOrderStatus(id);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        if(order.getStatus().equals("Delivre")){
            List<CartItemResponse> items = order.getCartItems();

            for(CartItemResponse item : items){
                try {
                    this.stockServiceFeignClient.getProductAndUpdateQuantity(item.getProduct().getProductSku(), item.getQuantity());
                } catch (MarketplaceException e) {
                    throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
                }
            }
        }
    }
}
