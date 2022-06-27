package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.CartItem;
import com.example.marketplaceclient.model.CartItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "https://houlala.herokuapp.com/api/cart-items", name = "cart-items")
public interface CartItemServiceFeignClient {

    @PostMapping("")
    CartItemDto addItem(@RequestBody CartItem newItem) throws MarketplaceException;

    @GetMapping("")
    List<CartItemDto> getAllItems() throws MarketplaceException;

    @DeleteMapping("/{id}")
    CartItemDto deleteItem(@PathVariable String id) throws MarketplaceException;

    @GetMapping("/getCartsFromUsers")
    List<CartItemDto> getAllCartFromUsers(@RequestParam String userId) throws MarketplaceException;

    @PatchMapping("/{id}")
    CartItemDto editCartItem(@PathVariable String id, @RequestBody CartItem newItem) throws MarketplaceException;

    @GetMapping("/cartItemCount/{id}")
    int getCartItemCount(@PathVariable String id) throws MarketplaceException;

    @DeleteMapping("/deleteMany")
    CartItemDto deleteItemsFromUser(@RequestParam String userId) throws MarketplaceException;
}
