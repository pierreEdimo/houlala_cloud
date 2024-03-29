package com.example.locationService.repositories;

import com.example.locationService.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findLocationsByCountryId(Long id);

    Optional<Location> findLocationByUniqueIdentifier(String uniqueIdentifier);

    Optional<Location> findLocationByUserId(String userId);

    @Query("SELECT s from Location as s WHERE s.userId = :userId AND s.isStore = true")
    List<Location> getStoreByOwnerId(String userId);

    @Query("SELECT s from Location as s WHERE s.isStore = true ")
    List<Location> getStore();

    @Query("SELECT s from Location as s where s.isStore = false ")
    List<Location> getLocations();

    @Query("Select s from Location as s where s.isStore = true  and s.categoryId = :categoryId")
    List<Location> getStoreByCategoryId(long categoryId);
}
