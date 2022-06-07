package com.example.locationService.repositories;

import com.example.locationService.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findLocationByCountryId(Long id);
}
