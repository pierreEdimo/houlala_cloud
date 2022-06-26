package com.example.stockservice.service;

import com.example.stockservice.exception.StockServiceException;
import com.example.stockservice.feign.ProductServiceFeignClient;
import com.example.stockservice.model.Origin;
import com.example.stockservice.model.Product;
import com.example.stockservice.model.ProductInformation;
import com.example.stockservice.model.Stock;
import com.example.stockservice.model.dto.CreateProductInfoDto;
import com.example.stockservice.model.dto.ProductDto;
import com.example.stockservice.repository.OriginRepository;
import com.example.stockservice.repository.ProductInformationRepository;
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
    private final OriginRepository originRepository;
    private final ProductServiceFeignClient feignClient;

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
    public ProductInformation addProduct(CreateProductInfoDto newInfo) {
        ProductInformation createdInfo = new ProductInformation(
                newInfo.getProductSku(),
                newInfo.getQuantity(),
                newInfo.getArrivalDate(),
                newInfo.getBuyingPrice(),
                newInfo.getOriginLabel().getLabel().toLowerCase(),
                newInfo.getLocationId()
        );

        Optional<Origin> origin = this.originRepository.findOriginByLabel(newInfo.getOriginLabel().getLabel());

        if (origin.isEmpty()) {
            return this.repository.save(createdInfo);
        } else {
            createdInfo.setOriginLabel(origin.get());
        }

        return this.repository.save(createdInfo);
    }

    @Override
    public List<ProductInformation> getAllInfos() {
        return this.repository.findAll();
    }

    @Override
    public Stock getStockFromLocationId(String locationId) {
        List<Product> productList;
        List<ProductDto> productDtoList = new ArrayList<>();
        List<ProductInformation> productInformations = this.repository.findProductInformationsByLocationId(locationId);

        try {
            productList = this.feignClient.getProductsByLocationId(locationId, 0);
        } catch (StockServiceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        for (ProductInformation information : productInformations) {
            for (Product product : productList) {
                if (product.getProductSku().equalsIgnoreCase(information.getProductSku())) {
                    productDtoList.add(this.toProductDto(information, product));
                }
            }
        }

        return new Stock(
                productDtoList,
                locationId
        );

    }

    @Override
    public void getInfoAndUpdateQuantity(String productSku, int quantity) {

        Optional<ProductInformation> informationOptional = this.repository.findProductInformationByProductSku(productSku);

        if(informationOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with sku " + productSku + " was not found" );
        }

        ProductInformation product = informationOptional.get();
        product.increaseQuantitySold(quantity);
        product.decreaseAvailableQuantity(quantity);

        this.repository.save(product);

    }

    private ProductDto toProductDto(ProductInformation information, Product product) {
        return new ProductDto(
                information.getProductSku(),
                product.getName(),
                information.getQuantity(),
                information.getArrivalDate(),
                information.getBuyingPrice(),
                information.getQuantitySold(),
                product.getImageUrl()
        );
    }
}
