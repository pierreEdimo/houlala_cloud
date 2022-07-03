package com.example.inventoryservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class InventoryItem {
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private int sollQuantity;

    private int istQuantity;

    private int deficit;

    private String productSku;

    public InventoryItem() {}

    public InventoryItem(
            int sollQuantity,
            int istQuantity,
            String productSku,
            String name
    ){
        this.name = name;
        this.sollQuantity = sollQuantity;
        this.istQuantity = istQuantity;
        this.productSku = productSku;
        this.deficit = this.istQuantity - this.sollQuantity;
    }
}
