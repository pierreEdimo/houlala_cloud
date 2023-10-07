package com.houlala.marketplace.product;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDto {
    private long id;
    private String name;
    private String description;
    private int weight;
    private int sellingPrice;
    private boolean bookMarked;
    private String imageUrl;
    private String productSku;
    private String locationUniqueId;
    private int quantity;
    private LocalDate arrivalDate = null;
    private double buyingPrice;
    private String originLabel;
    private int totalSell;
    private String locationName;
}
