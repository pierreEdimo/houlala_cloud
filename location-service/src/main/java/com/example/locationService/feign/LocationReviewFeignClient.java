package com.example.locationService.feign;

import com.example.locationService.exception.LocationServiceException;
import com.example.locationService.model.ReviewResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "review", path = "review")
public interface LocationReviewFeignClient {
    @GetMapping("/getAllReviewByLocationId/{locationId}")
    ReviewResponseDto getReviewsByLocation(@PathVariable int locationId) throws LocationServiceException;
}
