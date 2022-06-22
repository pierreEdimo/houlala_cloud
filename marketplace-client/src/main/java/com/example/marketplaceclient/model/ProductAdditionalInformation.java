package com.example.marketplaceclient.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProductAdditionalInformation {

    private String productSku;

    private int quantity;

    private LocalDate arrivalDate;

    private double buyingPrice;

    private Origin originLabel;

    public ProductAdditionalInformation(){}

    public ProductAdditionalInformation(
            String productSku,
            int quantity,
            double buyingPrice,
            String originLabel
    ){
        this.productSku = productSku;
        this.quantity = quantity;
        this.buyingPrice = buyingPrice;
        this.originLabel = new Origin(originLabel);
    }
}
