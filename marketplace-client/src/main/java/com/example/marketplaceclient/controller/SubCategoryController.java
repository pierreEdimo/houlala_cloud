package com.example.marketplaceclient.controller;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.feign.ProductTypeFeignClient;
import com.example.marketplaceclient.feign.UploadServiceFeignClient;
import com.example.marketplaceclient.model.SubCategory;
import com.example.marketplaceclient.model.dto.CreateSubCategoryDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sub-categories")
public class SubCategoryController {

    private final ProductTypeFeignClient feignClient;

    private final UploadServiceFeignClient uploadServiceFeignClient;

    @GetMapping
    public List<SubCategory> getAllSubCategories() {
        try {
            return this.feignClient.getAllProductTypes();
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PostMapping
    public SubCategory addSubCategory(@RequestPart String subCategory, @RequestPart MultipartFile image) {
        CreateSubCategoryDto subCategoryDto;
        String imageUrl;
        SubCategory newType;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            subCategoryDto = objectMapper.readValue(subCategory, CreateSubCategoryDto.class);
        } catch (IOException io) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, io.getMessage());
        }

        newType = new SubCategory(
                subCategoryDto.getLabel(),
                subCategoryDto.getCategoryId()
        );

        try {
            imageUrl = this.uploadServiceFeignClient.uploadIconImage(image);
            newType.setThumbNailUrl(imageUrl);
            return this.feignClient.addProductType(newType);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public SubCategory getSingleSubCategory(@PathVariable String id) {
        try {
            return this.feignClient.getSingleProductType(id);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public SubCategory deleteSubCategory(@PathVariable String id) {
        try {
            return this.feignClient.deleteProductType(id);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public SubCategory editSubCategory(@RequestBody SubCategory newType, @PathVariable String id) {
        try {
            return this.feignClient.editProductType(newType, id);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/category/{id}")
    public List<SubCategory> getTypesByCategoryId(@PathVariable("id") String categoryId) {
        try {
            return this.feignClient.getTypesByCategoryId(categoryId);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }
}
