package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.CartItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "https://houlala.herokuapp.com/api/cart-items", name = "cart-items")
public interface CartItemServiceFeignClient {

    @PostMapping("")
    CartItem addItem(@RequestBody CartItem newItem) throws MarketplaceException;

    @GetMapping("")
    List<CartItem> getAllItems() throws MarketplaceException;

    @DeleteMapping("/{id}")
    CartItem deleteItem(@PathVariable String id) throws MarketplaceException;

    @GetMapping("/getCartsFromUsers")
    List<CartItem> getAllCartFromUsers(@RequestParam String userId) throws MarketplaceException;

    @PatchMapping("/{id}")
    CartItem editCartItem(@PathVariable String id, @RequestBody CartItem newItem) throws MarketplaceException;

    @GetMapping("/cartItemCount/{id}")
    int getCartItemCount(@PathVariable String id) throws MarketplaceException;

    @DeleteMapping("/deleteMany")
    CartItem deleteItemsFromUser(@RequestParam String userId) throws MarketplaceException;
}
