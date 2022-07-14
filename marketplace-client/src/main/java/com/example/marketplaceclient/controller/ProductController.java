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
    public Product getSingleProduct(@PathVariable String id) {
        return this.productService.getProduct(id);
    }

    @GetMapping("/search")
    public List<ProductDto> searchProducts(@RequestParam String searchWord) {
        return this.productService.searchProduct(searchWord);
    }

    @GetMapping("/getRandomProducts")
    public List<Product> getRandomProducts(@RequestParam int size, @RequestParam String categoryId) {
        return this.productService.getRandomProducts(size, categoryId);
    }

    @GetMapping("/filterProductsByCategoryId")
    public List<Product> getProductsByCategoryId(@RequestParam String categoryId, @RequestParam(required = false) int limit) {
        return this.productService.getProductsByCategoryId(categoryId, limit);
    }

    @GetMapping("/filterProductsByCategoryAndProductType")
    public List<Product> getProductsByTypeAndCategoryId(@RequestParam String categoryId, @RequestParam String productType) {
        return this.productService.getProductsByTypeAndCategoryId(categoryId, productType);
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

    @PatchMapping("/addFavorite/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addProductToFavorite(@PathVariable String id, @RequestParam String userId) {
        this.productService.addProductToFavorite(userId, id);
    }

    @GetMapping("/favorites")
    public List<Product> getFavoritesProduct(@RequestParam String userId) {
        return this.productService.getFavoritesProduct(userId);
    }

    @GetMapping("/filterProductByPageId")
    public List<Product> getProductByLocationId(@RequestParam String locationId, @RequestParam int limit) {
        return this.productService.getProductByLocationId(locationId, limit);
    }

    @GetMapping("/productId/{id}")
    public ProductDto getProductByIdAndIsFavorite(@PathVariable String id, @RequestParam String userId) {
        return this.productService.getProductByIdAndIsFavorite(id, userId);
    }

    @GetMapping("/filterProductWithLimit")
    public List<Product> getProductsWithLimit(@RequestParam int limit) {
        return this.productService.getProductsWithLimit(limit);
    }

    @PostMapping("/createProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public String createProduct(@RequestPart(name = "product") String product, @RequestPart(name = "file") MultipartFile file) {
        return this.productService.createProduct(product, file);
    }

}

