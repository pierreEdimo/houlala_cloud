package com.houlala.marketplace.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts(){
        return this.productService.getAllProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestPart String product, @RequestPart MultipartFile image){
        this.productService.createProduct(product, image);
    }

    @GetMapping("{sku}/users/{userId}")
    public ProductDto getProductBySkuAndIsFavorite(@PathVariable String sku, @PathVariable String userId){
        return this.productService.getProductBySkuAndIsFavorite(sku, userId);
    }

    @GetMapping("{sku}")
    public ProductDto getProductBySku(@PathVariable String sku){
        return this.productService.getProductBySku(sku);
    }

    @GetMapping("/location/{id}")
    public List<ProductDto> getProductsByLocationId(@PathVariable String id, @RequestParam int limit){
        return this.productService.getProductsByLocationId(id, limit);
    }

    @GetMapping("/locations/{id}/size/{size}")
    public List<ProductDto> filterProdctsByLocationIdAndLimit(@PathVariable String id, @PathVariable int size){
        return this.productService.getProductsByLocationId(id, size);
    }

    @GetMapping("/categories/{id}/size/{size}")
    public List<ProductDto> getProductsByCategoryId(@PathVariable long id, @PathVariable int size){
        return this.productService.getProductsByCategoryId(id, size);
    }

    @GetMapping("/favorites/{userId}")
    public List<ProductDto> getProductsFavoritesList(@PathVariable String userId){
        return this.productService.getProductsFavoritesList(userId);
    }

    @PostMapping("/favorites/{id}/users/{userId}")
    public void addProductToFavorites(@PathVariable int id, @PathVariable String userId){
        this.productService.addProductToFavorites(id, userId);
    }

    @GetMapping("/sub-categories/{id}/size/{size}")
    public List<ProductDto> getProductsBySubCategoryId(@PathVariable int id, @PathVariable int size){
        return this.productService.getProductsBySubCategoryId(id, size);
    }

    @GetMapping("/random/locations/{id}/size/{size}")
    public List<ProductDto> getRandomProductsByLocationId(@PathVariable String id, @PathVariable int size){
        return this.productService.getRandomProductsByLocationId(id, size);
    }

    @GetMapping("/random/categories/{id}/size/{size}")
    public List<ProductDto> getRandomProductsByCategoryId(@PathVariable int id, @PathVariable int size){
        return this.productService.getRandomProductsByCategoryId(id, size);
    }

    @GetMapping("/search")
    public List<ProductDto> getFilteredProducts(@RequestParam String word){
        return this.productService.getFilteredProducts(word);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable int id){
        this.productService.deleteProduct(id);
    }

    @PutMapping("{id}")
    public void editProduct(@PathVariable int id, @RequestBody Product product){
        this.productService.editProduct(id, product);
    }

    @GetMapping("limit/{size}")
    public List<ProductDto> getProductsWithLimit(@PathVariable int size){
        return this.productService.getProductsWithLimit(size);
    }

    @GetMapping("/total/{luid}")
    public int getProductCountByLocationId(@PathVariable String luid){
        return this.productService.getProductsTotalCount(luid);
    }
}
