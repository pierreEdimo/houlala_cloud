package com.example.inventoryservice.service;

import com.example.inventoryservice.model.ProductInformation;
import com.example.inventoryservice.model.dto.CreateProductInfoDto;

import java.util.List;

public interface ProductInformationService {
    ProductInformation getProductInfoById(String id);

    ProductInformation editProductInfo(String id, ProductInformation newInfo);

    void deleteProduct(String id);

    ProductInformation addProduct(CreateProductInfoDto newInfor);

    List<ProductInformation> getAllInfos();
}
