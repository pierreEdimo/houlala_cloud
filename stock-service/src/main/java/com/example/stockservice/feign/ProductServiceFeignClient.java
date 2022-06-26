package com.example.stockservice.feign;

import com.example.stockservice.exception.StockServiceException;
import com.example.stockservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "https://houlala.herokuapp.com/api/products", name = "products")
public interface ProductServiceFeignClient {

    @GetMapping("/filterProductByPageId")
    List<Product> getProductsByLocationId(@RequestParam String locationId, @RequestParam int limit) throws StockServiceException;

}
