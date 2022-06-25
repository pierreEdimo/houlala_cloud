package com.example.inventoryservice.service;

import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.model.dto.InventoryDto;

import java.util.List;

public interface InventoryService {

   List<InventoryDto> getAllInventories();

   Inventory createInventory(Inventory newInventory);

   InventoryDto getSingleInventory(long id);

   void deleteInventory(long id);

   Inventory editInventory(Inventory inventory, long id);

   List<InventoryDto> getInventoriesByLocationId(String locationId);

}
