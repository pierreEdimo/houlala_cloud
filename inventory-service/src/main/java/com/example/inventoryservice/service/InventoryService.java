package com.example.inventoryservice.service;

import com.example.inventoryservice.model.Inventory;

import java.util.List;

public interface InventoryService {

    Inventory createInventory(Inventory newInventory);

    Inventory getASingleInventory(long id);

    List<Inventory> getAllInventories();

    List<Inventory> getInventoriesFromLocationId(String locationId);

    void deleteInventory(long id);

    Inventory editInventory(long id, Inventory newInventory);
}
