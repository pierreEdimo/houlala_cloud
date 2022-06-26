package com.example.stockservice.repository;

import com.example.stockservice.model.Origin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OriginRepository extends JpaRepository<Origin, Long> {

    Optional<Origin> findOriginByLabel(String label);

}
