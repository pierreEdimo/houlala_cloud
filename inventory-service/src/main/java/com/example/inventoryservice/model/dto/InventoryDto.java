package com.example.inventoryservice.model.dto;

import com.example.inventoryservice.model.InventoryItem;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class InventoryDto {
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    private Map<String, InventoryItem> productList;

    private int totalDeficit = 0;

    private String locationId;

    public InventoryDto(){}

    public InventoryDto(
                        LocalDateTime createdAt,
                        LocalDateTime updatedAt,
                        Map<String, InventoryItem> productList,
                        int totalDeficit,
                        String locationId){
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.productList = productList;
        this.totalDeficit = totalDeficit;
        this.locationId = locationId;
    }
}
