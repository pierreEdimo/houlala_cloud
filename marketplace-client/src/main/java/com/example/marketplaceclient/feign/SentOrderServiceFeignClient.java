package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.SentOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "https://houlala.herokuapp.com/api/sent-orders", name = "sent-orders")
public interface SentOrderServiceFeignClient {

    @GetMapping("")
    List<SentOrder> getAllOrders() throws MarketplaceException;

    @GetMapping("/getSentOrdersByEmail")
    List<SentOrder> getOrdersByEmail(@RequestParam String email ) throws MarketplaceException;

    @GetMapping("/getAllSentOrdersFromPageId")
    List<SentOrder> getOrdersByLocationId(@RequestParam String locationId) throws MarketplaceException;

    @GetMapping("/getSentOrdersByPageId")
    List<SentOrder> getOrdersByLocationIdAndToday(@RequestParam String locationId) throws MarketplaceException;

    @GetMapping("/filterOrderByStatus")
    List<SentOrder> getOrderByStatus(@RequestParam String locationId, @RequestParam String status) throws MarketplaceException;

    @PutMapping("/changeOrderStatus")
    SentOrder changeOrderStatus(@RequestParam String id) throws MarketplaceException;

}
