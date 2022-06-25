package com.example.inventoryservice.service;

import com.example.inventoryservice.exception.InventoryException;
import com.example.inventoryservice.feign.ProductFeignClient;
import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.model.Product;
import com.example.inventoryservice.model.ProductInformation;
import com.example.inventoryservice.model.dto.InventoryDto;
import com.example.inventoryservice.model.dto.ProductDto;
import com.example.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;

    private final ProductFeignClient feignClient;

    @Override
    public List<InventoryDto> getAllInventories() {
        List<Inventory> inventoryList = this.repository.findAll();
        List<InventoryDto> inventoryDtos = new ArrayList<>();
        inventoryList.forEach(inventory -> inventoryDtos.add(this.toInventoryDto(inventory)));
        return inventoryDtos;
    }

    @Override
    public Inventory createInventory(Inventory newInventory) {
        return this.repository.save(newInventory);
    }

    @Override
    public InventoryDto getSingleInventory(long id) {
        Optional<Inventory> inventoryOptional = this.repository.findById(id);

        if (inventoryOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory with Id " + id + " was not found");
        }

        return this.toInventoryDto(inventoryOptional.get());
    }

    @Override
    public void deleteInventory(long id) {
        Optional<Inventory> inventoryOptional = this.repository.findById(id);

        if (inventoryOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory with id " + id + " was not found");
        }

        this.repository.delete(inventoryOptional.get());

    }

    @Override
    public Inventory editInventory(Inventory inventory, long id) {
        Optional<Inventory> inventoryOptional = this.repository.findById(id);

        if (inventoryOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory with id " + id + " was not found");
        }

        Inventory existingInventory = inventoryOptional.get();
        existingInventory.setAvailableProducts(inventory.getAvailableProducts());

        return this.repository.save(existingInventory);
    }

    @Override
    public List<InventoryDto> getInventoriesByLocationId(String locationId) {
        List<Inventory> inventoryList = this.repository.findInventoriesByLocationId(locationId);
        List<InventoryDto> inventoryDtoList = new ArrayList<>();
        inventoryList.forEach(inventory -> inventoryDtoList.add(this.toInventoryDto(inventory)));
        return inventoryDtoList;
    }

    private InventoryDto toInventoryDto(Inventory inventory) {
        List<ProductInformation> infos = inventory.getAvailableProducts();
        List<Product> products;
        List<ProductDto> productDtoList = new ArrayList<>();

        try {
            products = this.feignClient.getAllProductsByLocationId(inventory.getLocationId(), 0);
        } catch (InventoryException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        for (Product product : products) {
            for (ProductInformation info : infos) {
                if (info.getProductSku().equalsIgnoreCase(product.getProductSku())) {
                    ProductDto productDto = new ProductDto(
                            info.getProductSku(),
                            product.getName(),
                            info.getQuantity(),
                            info.getArrivalDate(),
                            info.getBuyingPrice(),
                            info.getQuantitySold(),
                            product.getImageUrl()
                    );

                    productDtoList.add(productDto);
                }
            }
        }

        return new InventoryDto(
                inventory.getId(),
                inventory.getCreatedAt(),
                inventory.getUpdatedAt(),
                inventory.getLocationId(),
                productDtoList
        );
    }
}
