package com.example.inventoryservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Product {

    @Id
    private String productSku;

    private String name;

    private String imageUrl;

    private int solQuantity;

    private int isQuantity;

    private int deficit;
}
