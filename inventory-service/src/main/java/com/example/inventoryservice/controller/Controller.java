package com.example.inventoryservice.controller;

import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.model.dto.CreateInventoryDto;
import com.example.inventoryservice.model.dto.InventoryDto;
import com.example.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final InventoryService service;

    @PostMapping("/newInventory")
    public InventoryDto createInventory(@RequestBody CreateInventoryDto newInventory) {
        return this.service.createInventory(newInventory);
    }

    @GetMapping("/getSingleInventory/{id}")
    public InventoryDto getSingleInventory(@PathVariable long id) {
        return this.service.getASingleInventory(id);
    }

    @DeleteMapping("/deleteInventory/{id}")
    public void deleteInventory(@PathVariable long id) {
        this.service.deleteInventory(id);
    }

    @GetMapping("")
    public List<InventoryDto> getAllInventories() {
        return this.service.getAllInventories();
    }

    @GetMapping("/getInventoriesFromLocationId")
    public List<InventoryDto> getInventoriesFromLocationId(@RequestParam String locationId) {
        return this.service.getInventoriesFromLocationId(locationId);
    }

    @PutMapping("/editInventory/{id}")
    public InventoryDto editInventory(@PathVariable long id, @RequestBody CreateInventoryDto newInventory) {
        return this.service.editInventory(id, newInventory);
    }

}
