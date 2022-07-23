package com.example.inventoryservice.controller;

import com.example.inventoryservice.model.dto.CreateInventoryDto;
import com.example.inventoryservice.model.dto.InventoryDto;
import com.example.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventories")
public class Controller {

    private final InventoryService service;

    @PostMapping("")
    public InventoryDto createInventory(@RequestBody CreateInventoryDto newInventory) {
        return this.service.createInventory(newInventory);
    }

    @GetMapping("/{id}")
    public InventoryDto getSingleInventory(@PathVariable long id) {
        return this.service.getASingleInventory(id);
    }

    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable long id) {
        this.service.deleteInventory(id);
    }

    @GetMapping("")
    public List<InventoryDto> getAllInventories() {
        return this.service.getAllInventories();
    }

    @GetMapping("/location/{id}")
    public List<InventoryDto> getInventoriesFromLocationId(@PathVariable("id") String locationId) {
        return this.service.getInventoriesFromLocationId(locationId);
    }

    @PutMapping("/{id}")
    public InventoryDto editInventory(@PathVariable long id, @RequestBody CreateInventoryDto newInventory) {
        return this.service.editInventory(id, newInventory);
    }

}
