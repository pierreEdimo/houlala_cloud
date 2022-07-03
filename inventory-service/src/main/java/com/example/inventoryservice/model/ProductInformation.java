package com.example.inventoryservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInformation {
    private int quantity;
    private int quantitySold;
    private String productSku;
}
