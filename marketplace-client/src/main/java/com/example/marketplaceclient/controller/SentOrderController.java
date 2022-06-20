package com.example.marketplaceclient.controller;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.feign.SentOrderServiceFeignClient;
import com.example.marketplaceclient.model.SentOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sent-orders")
public class SentOrderController {

    private final SentOrderServiceFeignClient feignClient;

    @GetMapping("")
    public List<SentOrder> getAllOrders() {
        try {
            return this.feignClient.getAllOrders();
        } catch (MarketplaceException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getSentOrdersByEmail")
    public List<SentOrder> getOrdersByEmail(@RequestParam String email) {
        try {
            return this.feignClient.getOrdersByEmail(email);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/getAllSentOrdersFromPageId")
    public List<SentOrder> getOrdersByLocationId(@RequestParam String locationId) {
        try {
            return this.feignClient.getOrdersByLocationId(locationId);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/getSentOrdersByPageId")
    public List<SentOrder> getOrdersByLocationIdAndToday(@RequestParam String locationId) {
        try {
            return this.feignClient.getOrdersByLocationIdAndToday(locationId);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/filterOrderByStatus")
    public List<SentOrder> getOrderByStatus(@RequestParam String locationId, @RequestParam String status) {
        try {
            return this.feignClient.getOrderByStatus(locationId, status);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }
}
