package com.example.inventoryservice.feign;


import com.example.inventoryservice.exception.InventoryException;
import com.example.inventoryservice.model.ProductInformation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "stock", path = "stock")
public interface StockFeignClient {

    @GetMapping("/getInfoBySku")
    ProductInformation getProductBySKu(@RequestParam(value = "sku") String productSku) throws InventoryException;


}
