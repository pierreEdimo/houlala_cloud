package com.example.stockservice.model;

import com.example.stockservice.model.dto.ProductDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Stock {

    private List<ProductDto> productDtoList;

    private int productTotal;

    private double totalExpanses;

    private int totalSold;

    private String locationId;

    public Stock(
            List<ProductDto> productDtoList,
            String locationId
    ){
        this.productDtoList =  productDtoList;
        this.locationId = locationId;
        this.calCulateTotalProduct(this.productDtoList);
        this.calculateQuantitySold(this.productDtoList);
        this.calculateTotalExpense(this.productDtoList);

    }

    private void calCulateTotalProduct(List<ProductDto> productDtoList){
        for(ProductDto productDto: productDtoList){
            this.productTotal += productDto.getQuantity();
        }
    }

    private void calculateQuantitySold(List<ProductDto> productDtoList){
        for (ProductDto productDto: productDtoList){
            this.totalSold += productDto.getQuantitySold();
        }
    }

    private void calculateTotalExpense(List<ProductDto> productDtoList){
        for(ProductDto productDto: productDtoList){
            this.totalExpanses += productDto.getBuyingPrice();
        }
    }
}
