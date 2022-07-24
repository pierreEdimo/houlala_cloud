package com.example.marketplaceclient.controller;

import com.example.marketplaceclient.model.CreateProduct;
import com.example.marketplaceclient.model.Product;
import com.example.marketplaceclient.model.dto.ProductDto;
import com.example.marketplaceclient.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ProductDto getSingleProduct(@PathVariable String id) {
        return this.productService.getProduct(id);
    }

    @GetMapping("/search")
    public List<ProductDto> searchProducts(@RequestParam String searchWord) {
        return this.productService.searchProduct(searchWord);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Product deleteProduct(@PathVariable String id) {
        return this.productService.deleteProduct(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Product editProduct(@PathVariable String id, @RequestBody CreateProduct newProduct) {
        return this.productService.editProduct(id, newProduct);
    }

    @PostMapping("/favorites/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addProductToFavorite(@PathVariable String id, @RequestParam String userId) {
        this.productService.addProductToFavorite(userId, id);
    }

    @GetMapping("/favorites")
    public List<Product> getFavoritesProduct(@RequestParam String userId) {
        return this.productService.getFavoritesProduct(userId);
    }

    @GetMapping("/location/{id}")
    public List<ProductDto> getProductByLocationId(@PathVariable("id") String locationId, @RequestParam int limit) {
        return this.productService.getProductByLocationId(locationId, limit);
    }

    @GetMapping("/favorites/{id}")
    public ProductDto getProductByIdAndIsFavorite(@PathVariable String id, @RequestParam String userId) {
        return this.productService.getProductByIdAndIsFavorite(id, userId);
    }

    @GetMapping("/filter")
    public List<ProductDto> getProductsWithLimit(@RequestParam int limit) {
        return this.productService.getProductsWithLimit(limit);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public String createProduct(@RequestPart(name = "product") String product, @RequestPart(name = "file") MultipartFile file) {
        return this.productService.createProduct(product, file);
    }

    @GetMapping("/random/location/{id}")
    public List<ProductDto> getRandomProductsByLocationId(@PathVariable("id") String locationId, @RequestParam int size) {
        return this.productService.getRandomProductsByLocationId(locationId, size);
    }

    @GetMapping("/name/{name}")
    public ProductDto getProductByNameAndIsFavorite(@PathVariable("name") String name, @RequestParam String userId) {
        return this.productService.getProductByNameAndIsFavorite(name, userId);
    }

    @GetMapping("/type/{id}")
    public List<ProductDto> getProductsByType(@PathVariable("id") String typeId, @RequestParam int limit) {
        return this.productService.getProductsByType(typeId, limit);
    }

}

