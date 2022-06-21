package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.ProductAdditionalInformation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory", path = "inventory")
public interface InventoryServiceFeignClient {

    @GetMapping("/{id}")
    ProductAdditionalInformation getASingleInfo(@PathVariable String id) throws MarketplaceException;

}
