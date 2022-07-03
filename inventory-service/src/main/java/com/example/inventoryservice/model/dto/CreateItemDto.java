package com.example.inventoryservice.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateItemDto {
    private int istQuantity;
    private String productSku;
}
