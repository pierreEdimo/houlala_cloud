package com.houlala.marketplace.product;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateProduct {
    private String name;

    private String description;

    private String imageUrl;

    private int weight;

    private int sellingPrice;

    private String locationUniqueId;

    private int categoryId;

    private int subCategoryId;

    private String productSku;
}
