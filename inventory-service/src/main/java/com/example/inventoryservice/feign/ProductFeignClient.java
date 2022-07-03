package com.example.inventoryservice.feign;

import com.example.inventoryservice.exception.InventoryException;
import com.example.inventoryservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "https://houlala.herokuapp.com/api/products", name = "products")
public interface ProductFeignClient {

    @GetMapping("/getProductBySku")
    Product getSingleProduct(@RequestParam(name = "sku") String sku) throws InventoryException;

}
