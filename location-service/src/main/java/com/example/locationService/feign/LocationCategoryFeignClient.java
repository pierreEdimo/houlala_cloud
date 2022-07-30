package com.example.locationService.feign;

import com.example.locationService.exception.LocationServiceException;
import com.example.locationService.model.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "category", path = "category")
public interface LocationCategoryFeignClient {

    @GetMapping("/{id}")
    Category getSingleCategory(@PathVariable long id) throws LocationServiceException;
}
