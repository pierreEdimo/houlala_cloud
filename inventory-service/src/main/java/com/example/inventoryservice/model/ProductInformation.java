package com.example.inventoryservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class ProductInformation {

    @Id
    private String productId;

    private int quantity;

    private LocalDate arrivalDate;

    private double buyingPrice;

    private int quantitySold;

    @ManyToOne(targetEntity = Origin.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "origin_id", referencedColumnName = "id")
    private Origin originLabel;


    public ProductInformation() {
    }

    public ProductInformation(
            String productId,
            int quantity,
            LocalDate arrivalDate,
            double buyingPrice,
            String label
    ) {
        this.productId = productId;
        this.quantity = quantity;
        this.buyingPrice = buyingPrice;
        this.quantitySold = 0;
        this.arrivalDate = arrivalDate;
        this.originLabel = new Origin(label);
    }
}
