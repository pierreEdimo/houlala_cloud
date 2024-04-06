package com.houlala.marketplace.product;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    private long id;
    private String name;
    private String description;
    private int weight;
    private int sellingPrice;
    private boolean bookMarked;
    private String imageUrl;
    private String productSku;
    private String locationUniqueId;
    private int categoryId;
    private int subCategoryId;
}
