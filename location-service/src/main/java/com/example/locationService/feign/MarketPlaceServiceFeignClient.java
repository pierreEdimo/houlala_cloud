package com.example.locationService.feign;

import com.example.locationService.exception.LocationServiceException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "marketplace", path = "marketplace")
public interface MarketPlaceServiceFeignClient {

    @GetMapping("orders/count/locations/total/{luid}")
    long getTotalOrderCount(@PathVariable(value = "luid") String locationId) throws LocationServiceException;

    @GetMapping("orders/count/locations/sold/{luid}")
    long getOrderSoldCount(@PathVariable(value = "luid") String locationId) throws LocationServiceException;

    @GetMapping("orders/count/locations/canceled/{luid}")
    long getCanceledOrderCount(@PathVariable(value = "luid") String locationId) throws LocationServiceException;

    @GetMapping("/products/total/{luid}")
    long getProductTotalCount(@PathVariable(value = "luid") String locationId) throws LocationServiceException;
}
