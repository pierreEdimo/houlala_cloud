package com.example.roomservice.services;

import com.example.roomservice.model.RoomCategory;

import java.util.List;

public interface RoomCategoryService {

    List<RoomCategory> getAllCategories();

    void deleteRoomCategory(long id);

    RoomCategory editCategory(RoomCategory newCategory, long id);

}
