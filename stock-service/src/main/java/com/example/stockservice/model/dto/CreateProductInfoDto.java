package com.example.stockservice.model.dto;

import com.example.stockservice.model.Origin;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@Setter
public class CreateProductInfoDto {

    @Column(unique = true)
    private String productSku;

    private int quantity;

    private LocalDate arrivalDate;

    private double buyingPrice;

    private Origin originLabel;

    private String locationId;
}
