package com.houlala.marketplace.stock;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductAdditionalInformation {
    private String productSku;

    private int quantity;

    private LocalDate arrivalDate;

    private double buyingPrice;

    private Origin originLabel;

    private String locationId;
}
