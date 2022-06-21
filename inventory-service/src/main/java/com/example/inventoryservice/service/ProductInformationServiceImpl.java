package com.example.inventoryservice.service;

import com.example.inventoryservice.model.ProductInformation;
import com.example.inventoryservice.repository.ProductInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductInformationServiceImpl implements ProductInformationService {

    private final ProductInformationRepository repository;

    @Override
    public ProductInformation getProductInfoById(String id) {
        Optional<ProductInformation> optionalProductInformation = this.repository.findById(id);

        if (optionalProductInformation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product doesn't exist");
        }

        return optionalProductInformation.get();
    }

    @Override
    public ProductInformation editProductInfo(String id, ProductInformation newInfo) {
        Optional<ProductInformation> optionalProductInformation = this.repository.findById(id);

        if (optionalProductInformation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }

        ProductInformation info = optionalProductInformation.get();
        info.setArrivalDate(newInfo.getArrivalDate());
        info.setQuantity(newInfo.getQuantity());

        return this.repository.save(info);
    }

    @Override
    public void deleteProduct(String id) {
        Optional<ProductInformation> optionalProductInformation = this.repository.findById(id);

        if (optionalProductInformation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product doesn't exist");
        }

        ProductInformation info = optionalProductInformation.get();

        this.repository.delete(info);
    }

    @Override
    public ProductInformation addProduct(ProductInformation newInfo) {
        return this.repository.save(newInfo);
    }

    @Override
    public List<ProductInformation> getAllInfos() {
        Iterable<ProductInformation> informations = this.repository.findAll();
        List<ProductInformation> infos = new ArrayList<>();
        informations.forEach(infos::add);
        return infos;
    }
}
