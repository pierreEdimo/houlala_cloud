package com.example.marketplaceclient.controller;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.feign.CartItemServiceFeignClient;
import com.example.marketplaceclient.model.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart-Items")
public class CartItemController {

    private final CartItemServiceFeignClient feignClient;

    @GetMapping("")
    public List<CartItem> getAllItems(){
        try {
            return this.feignClient.getAllItems();
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public CartItem deleteItem(@PathVariable String id){
        try {
            return this.feignClient.deleteItem(id);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PostMapping("")
    public CartItem addItem(@RequestBody CartItem newItem){
        try {
            return this.feignClient.addItem(newItem);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/getCartsFromUsers")
    public List<CartItem> getAllCartFromUsers(@RequestParam String id){
        try {
            return this.feignClient.getAllCartFromUsers(id);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public CartItem editCartItem(@PathVariable String id, @RequestBody CartItem newItem){
        try {
            return this.feignClient.editCartItem(id, newItem);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/cartItemCount/{id}")
    int getCartItemCount(@PathVariable(name = "id") String userId){
        try {
            return this.feignClient.getCartItemCount(userId);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @DeleteMapping("/deleteMany")
    CartItem deleteItemsFromUser(@RequestParam String userId){
        try {
            return this.feignClient.deleteItemsFromUser(userId);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }


}
