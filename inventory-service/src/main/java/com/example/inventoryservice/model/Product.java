package com.example.inventoryservice.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Product {

    private String productSku;

    private int quantity;

    private String name;
}
