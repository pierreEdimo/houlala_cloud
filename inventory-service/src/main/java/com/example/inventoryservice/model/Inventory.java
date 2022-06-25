package com.example.inventoryservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate createdAt = LocalDate.now();

    private LocalDate updatedAt = LocalDate.now();

    private String locationId;

    @OneToMany(targetEntity = ProductInformation.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_information_id", referencedColumnName = "id")
    private List<ProductInformation> availableProducts;

    public Inventory(){}

    public Inventory(String locationId){
        this.locationId = locationId;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

}
