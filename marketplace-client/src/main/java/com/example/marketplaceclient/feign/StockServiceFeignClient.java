package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.ProductAdditionalInformation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "stock", path = "stock")
public interface StockServiceFeignClient {

    @GetMapping("/{id}")
    ProductAdditionalInformation getASingleInfo(@PathVariable("id") String id) throws MarketplaceException;

    @GetMapping("")
    List<ProductAdditionalInformation> getAllProductInfos() throws MarketplaceException;

    @PostMapping("/newProduct")
    ProductAdditionalInformation addInfo(@RequestBody ProductAdditionalInformation info) throws MarketplaceException;

    @PutMapping("/getInfoAndUdateQuantity")
    void getProductAndUpdateQuantity(@RequestParam("productSku") String productSku, @RequestParam("quantity") int quantity) throws MarketplaceException;

}
