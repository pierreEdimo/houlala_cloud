package com.houlala.marketplace.subCategories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.houlala.marketplace.upload.UploadFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/sub-categories")
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryFeign subCategoryFeign;
    private final UploadFeign uploadFeign;

    @GetMapping
    public List<SubCategory> getAllSubCategories(){
        return this.subCategoryFeign.getAllCategories();
    }

    @GetMapping("/{id}")
    public SubCategory getSingleCategory(@PathVariable long id){
        return this.subCategoryFeign.getSingleCategory(id);
    }

    @GetMapping("/categories/{id}")
    public List<SubCategory> getCategoriesByParentCategory(@PathVariable long id){
        return this.subCategoryFeign.getCategoriesByParentCategory(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable long id){
        this.subCategoryFeign.deleteCategory(id);
    }

    @PutMapping("/{id}")
    public void editCategory(@PathVariable long id, @RequestBody SubCategory subCategory){
        this.subCategoryFeign.editCategory(id, subCategory);
    }

    @PostMapping
    public SubCategory createSubCategory(@RequestPart String subCategory, @RequestPart MultipartFile image){
        MappedSubCategory mappedSubCategory;

        String imageUrl = this.uploadFeign.uploadProductThumbnail(image);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            mappedSubCategory = objectMapper.readValue(subCategory, MappedSubCategory.class);
        } catch (IOException io) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, io.getMessage());
        }

        CreateSubCategory newSubCategory = new CreateSubCategory(
                imageUrl,
                mappedSubCategory.getLabel(),
                mappedSubCategory.getCategory()
        );

        return this.subCategoryFeign.createSubCategory(newSubCategory);

    }
}

