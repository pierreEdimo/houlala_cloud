package com.example.inventoryservice.controller;

import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.model.dto.InventoryDto;
import com.example.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("")
    public List<InventoryDto> getAllInventories() {
        return this.inventoryService.getAllInventories();
    }

    @GetMapping("/{id}")
    public InventoryDto getSingleInventory(@PathVariable long id) {
        return this.inventoryService.getSingleInventory(id);
    }

    @PostMapping("")
    public Inventory addInventory(@RequestBody Inventory newInventory) {
        return this.inventoryService.createInventory(newInventory);
    }

    @GetMapping("/getAllInventoriesByLocationid")
    public List<InventoryDto> getAllInventorieByLocationId(@RequestParam(name = "locationId") String locationId) {
        return this.inventoryService.getInventoriesByLocationId(locationId);
    }

    @PutMapping("/{id}")
    public Inventory editInventory(@PathVariable long id, Inventory inventory) {
        return this.inventoryService.editInventory(inventory, id);
    }

    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable long id) {
        this.inventoryService.deleteInventory(id);
    }
}
