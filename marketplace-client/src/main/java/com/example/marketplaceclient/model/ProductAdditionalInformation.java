package com.example.marketplaceclient.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProductAdditionalInformation {

    private String productId;

    private int quantity;

    private LocalDate arrivalDate;

    private double buyingPrice;

    private Origin originLabel;
}
