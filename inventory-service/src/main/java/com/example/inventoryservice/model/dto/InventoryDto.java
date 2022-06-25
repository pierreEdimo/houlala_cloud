package com.example.inventoryservice.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class InventoryDto {
    private Long id;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private String locationId;

    private List<ProductDto> availableProducts;

    public InventoryDto(){}

    public InventoryDto(
            long id,
            LocalDate createdAt,
            LocalDate updatedAt,
            String locationId,
            List<ProductDto> availableProducts
    ){
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.locationId = locationId;
        this.availableProducts = availableProducts;
    }
}
