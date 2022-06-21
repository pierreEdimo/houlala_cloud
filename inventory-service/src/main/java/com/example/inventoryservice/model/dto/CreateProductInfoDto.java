package com.example.inventoryservice.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateProductInfoDto {

    private String productId;

    private int quantity;

    private LocalDate arrivalDate;

    private double buyingPrice;

    private String originLabel;
}
