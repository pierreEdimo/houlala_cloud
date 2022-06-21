package com.example.marketplaceclient.controller;

import com.example.marketplaceclient.model.Product;
import com.example.marketplaceclient.model.ProductResponse;
import com.example.marketplaceclient.model.dto.ProductDto;
import com.example.marketplaceclient.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("")
    public List<ProductDto> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse getSingleProduct(@PathVariable String id) {
        return this.productService.getProduct(id);
    }

    @GetMapping("/search")
    public List<ProductResponse> searchProducts(@RequestParam String searchWord) {
        return this.productService.searchProduct(searchWord);
    }

    @GetMapping("/getRandomProducts")
    public List<ProductResponse> getRandomProducts(@RequestParam int size, @RequestParam String categoryId) {
        return this.productService.getRandomProducts(size, categoryId);
    }

    @GetMapping("/filterProductsByCategoryId")
    public List<ProductResponse> getProductsByCategoryId(@RequestParam String categoryId, @RequestParam(required = false) int limit) {
        return this.productService.getProductsByCategoryId(categoryId, limit);
    }

    @GetMapping("/filterProductsByCategoryAndProductType")
    public List<ProductResponse> getProductsByTypeAndCategoryId(@RequestParam String categoryId, @RequestParam String productType) {
        return this.productService.getProductsByTypeAndCategoryId(categoryId, productType);
    }

    @DeleteMapping("/{id}")
    public ProductResponse deleteProduct(@PathVariable String id) {
        return this.productService.deleteProduct(id);
    }

    @PatchMapping("/{id}")
    public ProductResponse editProduct(@PathVariable String id, @RequestBody Product newProduct) {
        return this.productService.editProduct(id, newProduct);
    }

    @PatchMapping("/addFavorite/{id}")
    public void addProductToFavorite(@PathVariable String id, @RequestParam String userId) {
        this.productService.addProductToFavorite(userId, id);
    }

    @GetMapping("/favorites")
    public List<ProductResponse> getFavoritesProduct(@RequestParam String userId) {
        return this.productService.getFavoritesProduct(userId);
    }

    @GetMapping("/filterProductByPageId")
    public List<ProductResponse> getProductByLocationId(@RequestParam String locationId, @RequestParam int limit) {
        return this.productService.getProductByLocationId(locationId, limit);
    }

    @GetMapping("/productId/{id}")
    public ProductDto getProductByIdAndIsFavorite(@PathVariable String id, @RequestParam String userId) {
        return this.productService.getProductByIdAndIsFavorite(id, userId);
    }

    @GetMapping("/filterProductWithLimit")
    public List<ProductResponse> getProductsWithLimit(@RequestParam int limit){
        return this.productService.getProductsWithLimit(limit);
    }

}
