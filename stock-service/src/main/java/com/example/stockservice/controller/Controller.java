package com.example.stockservice.controller;

import com.example.stockservice.model.ProductInformation;
import com.example.stockservice.model.Stock;
import com.example.stockservice.model.dto.CreateProductInfoDto;
import com.example.stockservice.service.ProductInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final ProductInformationService service;

    @GetMapping("/{id}")
    public ProductInformation getSingleInfo(@PathVariable String id) {
        return this.service.getProductInfoById(id);
    }

    @PostMapping("/newProduct")
    public ProductInformation addProductInfo(@RequestBody CreateProductInfoDto info) {
        return this.service.addProduct(info);
    }

    @DeleteMapping("/{id}")
    public void deleteInfo(@PathVariable String id) {
        this.service.deleteProduct(id);
    }

    @PatchMapping("/{id}")
    public ProductInformation editProductInfo(@RequestBody ProductInformation info, @PathVariable String id) {
        return this.service.editProductInfo(id, info);
    }

    @GetMapping("")
    public List<ProductInformation> getAllInfos() {
        return this.service.getAllInfos();
    }

    @GetMapping("/getStock")
    public Stock getStockFromLocationId(@RequestParam String locationId){
        return this.service.getStockFromLocationId(locationId);
    }

    @PutMapping("/getInfoAndUdateQuantity")
    public void updateQuantitySoldForProduct(@RequestParam String productSku, @RequestParam int quantity){
        this.service.getInfoAndUpdateQuantity(productSku, quantity);
    }

}
