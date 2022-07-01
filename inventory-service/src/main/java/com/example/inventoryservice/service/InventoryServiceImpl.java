package com.example.inventoryservice.service;

import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public Inventory createInventory(Inventory newInventory) {
        newInventory.calculateDeficit();
        return this.inventoryRepository.save(newInventory);
    }

    @Override
    public Inventory getASingleInventory(long id) {
        Optional<Inventory> optionalInventory = this.inventoryRepository.findById(id);

        if (optionalInventory.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found");
        }

        return optionalInventory.get();
    }

    @Override
    public List<Inventory> getAllInventories() {
        return this.inventoryRepository.findAll();
    }

    @Override
    public List<Inventory> getInventoriesFromLocationId(String locationId) {
        return this.inventoryRepository.findInventoriesByLocationId(locationId);
    }

    @Override
    public void deleteInventory(long id) {
        Optional<Inventory> optionalInventory = this.inventoryRepository.findById(id);

        if (optionalInventory.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found");
        }

        this.inventoryRepository.delete(optionalInventory.get());
    }

    @Override
    public Inventory editInventory(long id, Inventory newInventory) {
        Optional<Inventory> optionalInventory = this.inventoryRepository.findById(id);

        if (optionalInventory.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found");
        }

        optionalInventory.get().setUpdatedAt(LocalDateTime.now());
        optionalInventory.get().setProductList(newInventory.getProductList());
        newInventory.calculateDeficit();
        optionalInventory.get().setTotalDeficit(newInventory.getTotalDeficit());

        return this.inventoryRepository.save(optionalInventory.get());
    }
}
