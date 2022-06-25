package com.example.inventoryservice.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProductDto {
    private String productSku;
    private String name;
    private int quantity;
    private LocalDate arrivalDate;
    private double buyingPrice;
    private int quantitySold;
    private String imageUrl;

    public ProductDto(){}

    public ProductDto(
            String productSku,
            String name,
            int quantity,
            LocalDate arrivalDate,
            double buyingPrice,
            int quantitySold,
            String imageUrl
    ){
        this.productSku = productSku;
        this.name = name;
        this.quantity = quantity;
        this.arrivalDate = arrivalDate;
        this.buyingPrice = buyingPrice;
        this.quantitySold = quantitySold;
        this.imageUrl = imageUrl;
    }
}
