package com.example.inventoryservice.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class ProductInformation {

    @Id
    private String productId;

    private int quantity;

    private LocalDateTime arrivalDate;

    private double buyingPrice;

    private int quantitySold;

    private String originLabel;



    public ProductInformation(){}

    public ProductInformation(
            String productId,
            int quantity,
            LocalDateTime arrivalDate,
            int buyingPrice,
            String originLabel
    ){
        this.productId = productId;
        this.quantity = quantity;
        this.buyingPrice = buyingPrice;
        this.quantitySold = 0;
        this.originLabel = originLabel;
    }
}
