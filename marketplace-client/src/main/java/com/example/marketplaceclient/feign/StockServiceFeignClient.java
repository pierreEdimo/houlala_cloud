package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.ProductAdditionalInformation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "stock", path = "stock")
public interface StockServiceFeignClient {

    @GetMapping("/{id}")
    ProductAdditionalInformation getASingleInfo(@PathVariable String id) throws MarketplaceException;

    @GetMapping("")
    List<ProductAdditionalInformation> getAllProductInfos() throws MarketplaceException;

    @PostMapping("/newProduct")
    ProductAdditionalInformation addInfo(@RequestBody ProductAdditionalInformation info) throws MarketplaceException;

}
