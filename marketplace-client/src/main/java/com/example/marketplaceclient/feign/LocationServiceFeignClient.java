package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.Location;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "location", path = "location")
public interface LocationServiceFeignClient {
    @GetMapping("/uniqueIdentifier/{uid}")
    Location getALocation(@PathVariable("uid") String uniqueIdentifier) throws MarketplaceException;
    
}
