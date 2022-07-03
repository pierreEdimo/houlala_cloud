package com.example.stockservice.service;

import com.example.stockservice.model.ProductInformation;
import com.example.stockservice.model.Stock;
import com.example.stockservice.model.dto.CreateProductInfoDto;

import java.util.List;

public interface ProductInformationService {
    ProductInformation getProductInfoById(String id);

    ProductInformation editProductInfo(String id, ProductInformation newInfo);

    void deleteProduct(String id);

    ProductInformation addProduct(CreateProductInfoDto newInfos);

    List<ProductInformation> getAllInfos();

    Stock getStockFromLocationId(String locationId);

    void getInfoAndUpdateQuantity(String productSku, int quantity);

    ProductInformation getInfoBySku(String productSku);

}
