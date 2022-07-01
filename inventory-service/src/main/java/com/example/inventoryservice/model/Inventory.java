package com.example.inventoryservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToMany(targetEntity = Product.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id", referencedColumnName = "productSku")
    private List<Product> productList;

    private int totalDeficit;

    private String locationId;

    public Inventory(){
        this.calculateDeficit();
    }

    public Inventory(List<Product> productList,
                     String locationId){
        this.productList = productList;
        this.locationId = locationId;
        this.calculateDeficit();
    }

    public void calculateDeficit(){
        for(Product product: this.productList){
            this.totalDeficit += product.getDeficit();
        }
    }
}
