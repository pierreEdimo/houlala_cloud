package com.houlala.marketplace.location;


import com.houlala.marketplace.exception.MarketplaceException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "location", path = "location")
public interface LocationFeign {
    @GetMapping("/uniqueIdentifier/{uid}")
    Location getALocation(@PathVariable(value = "uid") String uniqueIdentifier) throws MarketplaceException;
}
