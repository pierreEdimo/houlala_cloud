package com.houlala.marketplace.category;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.houlala.marketplace.upload.UploadFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryFeign categoryFeign;
    private final UploadFeign uploadFeign;

    @GetMapping
    public List<Category> getAllCategories(){
        return this.categoryFeign.getAllCategories();
    }

    @GetMapping("/random/{size}")
    public List<Category> getRandomCategories(@PathVariable int size){
        return this.categoryFeign.getRandomCategories(size);
    }

    @GetMapping("/limit/{size}")
    public List<Category> getCategoriesWithLimit(@PathVariable int size){
        return this.categoryFeign.getCategoriesWithLimit(size);
    }

    @GetMapping("/search")
    public List<Category> getFilterCategories(@RequestParam String searchWord){
        return this.categoryFeign.getFilteredCategories(searchWord);
    }

    @GetMapping("/{id}")
    public Category getSingleCategory(@PathVariable long id){
        return this.categoryFeign.getSingleCategory(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable long id){
        this.categoryFeign.deleteCategory(id);
    }

    @PutMapping("/{id}")
    public void editCategory(@PathVariable long id, @RequestBody Category category){
        this.categoryFeign.editCategory(id, category);
    }

    @PostMapping
    public Category createCategory(@RequestPart String category, @RequestPart MultipartFile image){
        MappedCategory mappedCategory;
        String imageUrl = this.uploadFeign.uploadProductThumbnail(image);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            mappedCategory = objectMapper.readValue(category, MappedCategory.class);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        CreateCategory newCategory = new CreateCategory(
                mappedCategory.getName(),
                imageUrl
        );

        return this.categoryFeign.createNewCategory(newCategory);
    }
}
