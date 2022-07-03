package com.example.inventoryservice.model.dto;

import com.example.inventoryservice.model.InventoryItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateInventoryDto {
    private String locationId;

    private List<CreateItemDto> productLists;
}
