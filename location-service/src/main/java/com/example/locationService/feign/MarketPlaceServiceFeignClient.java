package com.example.locationService.feign;

import com.example.locationService.exception.LocationServiceException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "marketplace", path = "marketplace")
public interface MarketPlaceServiceFeignClient {

    @GetMapping("orders/total/{locationId}")
    long getTotalOrderCount(@PathVariable String locationId) throws LocationServiceException;

    @GetMapping("orders/sold/{locationId}")
    long getOrderSoldCount(@PathVariable String locationId) throws LocationServiceException;

    @GetMapping("orders/canceled/{locationId}")
    long getCanceledOrderCount(@PathVariable String locationId) throws LocationServiceException;

    @GetMapping("/products/total/{locationId}")
    long getProductTotalCount(@PathVariable String locationId) throws LocationServiceException;
}
