package com.example.inventoryservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    private String locationId;

    @OneToMany(targetEntity = ProductInformation.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_information_id", referencedColumnName = "id")
    private List<ProductInformation> availableProducts;


}
