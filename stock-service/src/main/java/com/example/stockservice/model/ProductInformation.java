package com.example.stockservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class ProductInformation {

    @Id
    @Column(unique = true)
    private String productSku;

    private int quantity;

    private LocalDate arrivalDate;

    private double buyingPrice;

    private int quantitySold;

    private String locationId;

    @ManyToOne(targetEntity = Origin.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "origin_id", referencedColumnName = "id")
    private Origin originLabel;


    public ProductInformation() {
    }

    public ProductInformation(
            String productSku,
            int quantity,
            LocalDate arrivalDate,
            double buyingPrice,
            String label,
            String locationId
    ) {
        this.productSku = productSku;
        this.quantity = quantity;
        this.buyingPrice = buyingPrice;
        this.quantitySold = 0;
        this.arrivalDate = arrivalDate;
        this.originLabel = new Origin(label);
        this.locationId = locationId;
    }

    public void increaseQuantitySold(int quantity){
        this.quantitySold += quantity;
    }

    public void decreaseAvailableQuantity(int quantitySold){
        this.quantity -= quantitySold;
    }
}
