package com.example.inventoryservice.service;

import com.example.inventoryservice.model.dto.CreateInventoryDto;
import com.example.inventoryservice.model.dto.InventoryDto;

import java.util.List;

public interface InventoryService {

    InventoryDto createInventory(CreateInventoryDto newInventory);

    InventoryDto getASingleInventory(long id);

    List<InventoryDto> getAllInventories();

    List<InventoryDto> getInventoriesFromLocationId(String locationId);

    void deleteInventory(long id);

    InventoryDto editInventory(long id, CreateInventoryDto newInventory);
}
