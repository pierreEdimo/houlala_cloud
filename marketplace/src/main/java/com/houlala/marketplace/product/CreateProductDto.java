package com.houlala.marketplace.product;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDto {
    private String name;
    private String description;
    private int weight;
    private int sellingPrice;
    private int categoryId;
    private String locationUniqueId;
    private int subCategoryId;
    private int quantity;
    private double buyingPrice;
    private String originLabel;
}
