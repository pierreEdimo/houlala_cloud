package com.example.roomservice.services;

import com.example.roomservice.model.RoomCategory;
import com.example.roomservice.repositories.RoomCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomCategoryServiceImpl implements RoomCategoryService {

    private final RoomCategoryRepository repository;


    @Override
    public List<RoomCategory> getAllCategories() {
        List<RoomCategory> categoryList = new ArrayList<>();

        Iterable<RoomCategory> categoryIterable = this.repository.findAll();

        categoryIterable.forEach(categoryList::add);

        return categoryList;
    }

    @Override
    public void deleteRoomCategory(long id) {
        Optional<RoomCategory> categoryOptional = this.repository.findById(id);

        if (categoryOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }

        this.repository.delete(categoryOptional.get());
    }

    @Override
    public RoomCategory editCategory(RoomCategory newCategory, long id) {
        Optional<RoomCategory> categoryOptional = this.repository.findById(id);

        if(categoryOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }

        RoomCategory category = categoryOptional.get();
        category.setLabel(newCategory.getLabel());

        return this.repository.save(category);
    }
}
