package com.example.marketplaceclient.controller;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.feign.ProductTypeFeignClient;
import com.example.marketplaceclient.model.ProductType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.ws.rs.Path;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product-types")
public class ProductTypeController {

    private final ProductTypeFeignClient feignClient;

    @GetMapping("")
    public List<ProductType> getAllProductTypes() {
        try {
            return this.feignClient.getAllProductTypes();
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PostMapping("")
    public ProductType addProductType(@RequestBody ProductType newType) {
        try {
            return this.feignClient.addProductType(newType);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ProductType getSingleProductType(@PathVariable String id){
        try {
            return this.feignClient.getSingleProductType(id);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ProductType deleteProductType(@PathVariable String id){
        try {
            return this.feignClient.deleteProductType(id);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ProductType editProductType(@RequestBody ProductType newType, @PathVariable String id){
        try {
            return this.feignClient.editProductType(newType, id);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }
}
