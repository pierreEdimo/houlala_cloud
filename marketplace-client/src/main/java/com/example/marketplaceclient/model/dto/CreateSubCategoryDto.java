package com.example.marketplaceclient.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSubCategoryDto {
    private String label;
    private int categoryId;
}
