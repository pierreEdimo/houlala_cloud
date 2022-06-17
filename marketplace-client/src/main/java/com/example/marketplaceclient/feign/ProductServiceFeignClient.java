package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url = "https://houlala.herokuapp.com/api/products", name = "products")
public interface ProductServiceFeignClient {

    @GetMapping("")
    List<Product> getAllProducts();
}
