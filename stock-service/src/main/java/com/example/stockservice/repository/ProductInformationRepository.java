package com.example.stockservice.repository;

import com.example.stockservice.model.ProductInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInformationRepository extends JpaRepository<ProductInformation, String> {

    List<ProductInformation> findProductInformationsByLocationId(String locationId);

}
