package com.example.roomservice.repositories;

import com.example.roomservice.model.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomCategoryRepository extends JpaRepository<RoomCategory, Long> {
    Optional<RoomCategory> findByLabel(String label);
}
