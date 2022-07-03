package com.example.inventoryservice.service;

import com.example.inventoryservice.exception.InventoryException;
import com.example.inventoryservice.feign.ProductFeignClient;
import com.example.inventoryservice.feign.StockFeignClient;
import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.model.InventoryItem;
import com.example.inventoryservice.model.Product;
import com.example.inventoryservice.model.ProductInformation;
import com.example.inventoryservice.model.dto.CreateInventoryDto;
import com.example.inventoryservice.model.dto.CreateItemDto;
import com.example.inventoryservice.model.dto.InventoryDto;
import com.example.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    private final ProductFeignClient feignClient;

    private final StockFeignClient stockFeignClient;

    @Override
    public InventoryDto createInventory(CreateInventoryDto newInventory) {
        List<CreateItemDto> items = newInventory.getProductLists();
        Product product;
        ProductInformation information;
        List<InventoryItem> newItems = new ArrayList<>();

        for (CreateItemDto item : items) {
            try {
                product = this.feignClient.getSingleProduct(item.getProductSku());
                information = this.stockFeignClient.getProductBySKu(item.getProductSku());

            } catch (InventoryException e) {
                throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
            }


            if (item.getProductSku().equalsIgnoreCase(product.getProductSku())) {
                if (item.getProductSku().equalsIgnoreCase(information.getProductSku())) {
                    InventoryItem createdItem = new InventoryItem(
                            information.getQuantity(),
                            item.getIstQuantity(),
                            item.getProductSku(),
                            product.getName()
                    );
                    newItems.add(createdItem);
                }
            }
        }

        Inventory inventory = new Inventory(
                newInventory.getLocationId(),
                newItems);

        this.inventoryRepository.save(inventory);

        return this.toInventoryDto(inventory);


    }

    @Override
    public InventoryDto getASingleInventory(long id) {
        Optional<Inventory> optionalInventory = this.inventoryRepository.findById(id);

        if (optionalInventory.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found");
        }

        return this.toInventoryDto(optionalInventory.get());
    }

    @Override
    public List<InventoryDto> getAllInventories() {
        List<InventoryDto> inventoryDtoList = new ArrayList<>();
        List<Inventory> inventories = this.inventoryRepository.findAll();
        inventories.forEach(inventory -> inventoryDtoList.add(this.toInventoryDto(inventory)));
        return inventoryDtoList;
    }

    @Override
    public List<InventoryDto> getInventoriesFromLocationId(String locationId) {
        List<Inventory> inventories = this.inventoryRepository.findInventoriesByLocationId(locationId);
        List<InventoryDto> inventoryDtoList = new ArrayList<>();
        inventories.forEach(inventory -> inventoryDtoList.add(this.toInventoryDto(inventory)));
        return inventoryDtoList;
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
    public InventoryDto editInventory(long id, CreateInventoryDto newInventory) {
        Optional<Inventory> optionalInventory = this.inventoryRepository.findById(id);

        if (optionalInventory.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found");
        }

        Inventory inventory = optionalInventory.get();
        List<InventoryItem> items = inventory.getProductList();

        for(InventoryItem item: items){
            for(CreateItemDto newItem: newInventory.getProductLists()){
                if(item.getProductSku().equalsIgnoreCase(newItem.getProductSku())){
                    item.setIstQuantity(newItem.getIstQuantity());
                }

            }
        }

        inventory.setProductList(items);

       this.inventoryRepository.save(inventory);

       return this.toInventoryDto(inventory);
    }

    private InventoryDto toInventoryDto(Inventory inventory) {

        List<InventoryItem> items = inventory.getProductList();

        Map<String, InventoryItem> createdItems = items.stream().collect(Collectors.toMap(InventoryItem::getProductSku, Function.identity(), (a, b) -> new InventoryItem(
                a.getSollQuantity(),
                a.getIstQuantity() + b.getIstQuantity(),
                a.getProductSku(),
                a.getName()
        )));

        return new InventoryDto(
                inventory.getCreatedAt(),
                inventory.getUpdatedAt(),
                createdItems,
                inventory.getTotalDeficit(),
                inventory.getLocationId()
        );

    }
}
