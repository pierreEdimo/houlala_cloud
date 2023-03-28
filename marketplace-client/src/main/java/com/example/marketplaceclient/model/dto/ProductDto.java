package com.example.marketplaceclient.model.dto;

import com.example.marketplaceclient.model.Origin;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String _id;
    private String name;
    private String description;
    private int weight;
    private String imageUrl;
    private int sellingPrice;
    private String locationId;
    private boolean bookMarked;
    private int quantity;
    private LocalDate arrivalDate;
    private double buyingPrice;
    private String originLabel;
    private String productSku;
    private String locationName;
    private int totalSell; 
}
