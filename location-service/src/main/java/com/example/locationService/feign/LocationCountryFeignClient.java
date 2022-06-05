package com.example.locationService.feign;

import com.example.locationService.exception.LocationServiceException;
import com.example.locationService.model.Country;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "country", path = "country")
public interface LocationCountryFeignClient {

    @GetMapping("/getSingleCountry/{id}")
    Country getASingleCountry(@PathVariable long id) throws LocationServiceException;

}
