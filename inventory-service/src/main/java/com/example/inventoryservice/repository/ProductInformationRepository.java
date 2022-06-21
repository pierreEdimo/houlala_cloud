package com.example.inventoryservice.repository;

import com.example.inventoryservice.model.ProductInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInformationRepository extends JpaRepository<ProductInformation, String> {
}
