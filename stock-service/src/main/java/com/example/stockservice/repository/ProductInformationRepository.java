package com.example.stockservice.repository;

import com.example.stockservice.model.ProductInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductInformationRepository extends JpaRepository<ProductInformation, String> {

    List<ProductInformation> findProductInformationsByLocationId(String locationId);

    Optional<ProductInformation> findProductInformationByProductSku(String productSku);

}
