package com.example.inventoryservice.feign;

import com.example.inventoryservice.exception.InventoryException;
import com.example.inventoryservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "https://houlala.herokuapp.com/api/products", name = "products")
public interface ProductFeignClient {
    @GetMapping("")
    List<Product> getAllProducts() throws InventoryException;

    @GetMapping("/{id]")
    Product getSingleproduct() throws InventoryException;

    @GetMapping("/filterProductByPageId")
    List<Product> getAllProductsByLocationId(@RequestParam String LocationId, @RequestParam int limit) throws InventoryException;
 }
