package com.example.inventoryservice.controller;

import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final InventoryService service;

    @PostMapping("/newInventory")
    public Inventory createInventory(@RequestBody Inventory newInventory){
        return this.service.createInventory(newInventory);
    }

    @GetMapping("/getSingleInventory/{id}")
    public Inventory getSingleInventory(@PathVariable long id){
        return this.service.getASingleInventory(id);
    }

    @DeleteMapping("/deleteInventory/{id}")
    public void deleteInventory(@PathVariable long id){
        this.service.deleteInventory(id);
    }

    @GetMapping("")
    public List<Inventory> getAllInventories(){
        return this.service.getAllInventories();
    }

    @GetMapping("/getInventoriesFromLocationId")
    public List<Inventory> getInventoriesFromLocationId(@RequestParam String locationId){
        return this.service.getInventoriesFromLocationId(locationId);
    }

    @PutMapping("/editInventory/{id}")
    public Inventory editInventory(@PathVariable long id, @RequestBody Inventory newInventory){
        return this.service.editInventory(id,newInventory);
    }

}
