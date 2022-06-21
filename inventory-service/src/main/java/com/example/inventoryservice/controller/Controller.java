package com.example.inventoryservice.controller;

import com.example.inventoryservice.model.ProductInformation;
import com.example.inventoryservice.service.ProductInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class Controller {

    private final ProductInformationService service;

    @GetMapping("/{id}")
    public ProductInformation getSingleInfo(@PathVariable String id){
        return this.service.getProductInfoById(id);
    }

    @PostMapping("/newProduct")
    public ProductInformation addProductInfo(@RequestBody ProductInformation info){
        return this.service.addProduct(info);
    }

    @DeleteMapping("/{id}")
    public void deleteInfo(@PathVariable String id){
        this.service.deleteProduct(id);
    }

    @PatchMapping("/{id}")
    public ProductInformation editProductInfo(@RequestBody ProductInformation info, @PathVariable String id){
        return this.service.editProductInfo(id, info);
    }

    @GetMapping("")
    public List<ProductInformation> getAllInfos(){
        return this.service.getAllInfos();
    }
}
