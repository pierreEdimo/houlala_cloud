package com.example.marketplaceclient.model.dto;

import com.example.marketplaceclient.model.Origin;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
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

    public ProductDto(
            String id,
            String name,
            String description,
            int weight,
            String imageUrl,
            int sellingPrice,
            String locationId,
            boolean bookMarked,
            int quantity,
            LocalDate arrivalDate,
            double buyingPrice,
            Origin originLabel
    ){
        this._id = id;
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.imageUrl = imageUrl;
        this.sellingPrice = sellingPrice;
        this.locationId = locationId;
        this.bookMarked = bookMarked;
        this.quantity = quantity;
        this.arrivalDate = arrivalDate;
        this.buyingPrice = buyingPrice;
        this.originLabel = originLabel.getLabel();
    }
}
