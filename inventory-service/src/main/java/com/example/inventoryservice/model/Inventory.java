package com.example.inventoryservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToMany(targetEntity = InventoryItem.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private List<InventoryItem> productList;

    private int totalDeficit = 0;

    private String locationId;

    public Inventory(){}

    public Inventory(
            String locationId,
            List<InventoryItem> productList
    ){
        this.locationId = locationId;
        this.productList = productList;
        this.totalDeficit = calculateDeficit();
    }

    private int calculateDeficit(){
        if(this.productList != null){
            for (InventoryItem item : productList){
                this.totalDeficit +=  item.getDeficit();
            }
        }
        return this.totalDeficit;
    }

}
