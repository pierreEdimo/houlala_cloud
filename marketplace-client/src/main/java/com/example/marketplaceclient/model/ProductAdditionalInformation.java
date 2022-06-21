package com.example.marketplaceclient.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductAdditionalInformation {

    private int quantity;

    private LocalDateTime arrivalDate;

    private double buyingPrice;

    private String originLabel;
}
