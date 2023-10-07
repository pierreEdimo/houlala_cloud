package com.houlala.marketplace.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.houlala.marketplace.location.Location;
import com.houlala.marketplace.location.LocationFeign;
import com.houlala.marketplace.order.OrderFeign;
import com.houlala.marketplace.order.SellReport;
import com.houlala.marketplace.stock.Origin;
import com.houlala.marketplace.stock.ProductAdditionalInformation;
import com.houlala.marketplace.stock.StockFeignClient;
import com.houlala.marketplace.upload.UploadFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductFeign productFeignClient;

    private final StockFeignClient stockFeignClient;

    private final LocationFeign locationFeign;

    private final UploadFeign uploadFeign;

    private final OrderFeign orderFeign;

    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductDto> productDtos = new ArrayList<>();
        List<Product> products = this.productFeignClient.getAllProducts();
        products.forEach((product) -> productDtos.add(this.toProductDto(product)));
        return productDtos;
    }

    @Override
    public void createProduct(String product, MultipartFile image) {
        CreateProductDto productDto;
        String imageUrl;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            productDto = objectMapper.readValue(product, CreateProductDto.class);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        imageUrl = this.uploadFeign.uploadProductThumbnail(image);
        String productSku = this.skuGenerator(productDto.getName(), productDto.getOriginLabel(), productDto.getLocationUniqueId());

        CreateProduct createdProduct = new CreateProduct(
                productDto.getName(),
                productDto.getDescription(),
                imageUrl,
                productDto.getWeight(),
                productDto.getSellingPrice(),
                productDto.getLocationUniqueId(),
                productDto.getCategoryId(),
                productDto.getSubCategoryId(),
                productSku
        );

        ProductAdditionalInformation info = new ProductAdditionalInformation(
                productSku,
                productDto.getQuantity(),
                null,
                productDto.getBuyingPrice(),
                new Origin(productDto.getOriginLabel()),
                productDto.getLocationUniqueId()
        );

        this.stockFeignClient.addInfo(info);
        this.productFeignClient.createProduct(createdProduct);

    }

    @Override
    public ProductDto getProductBySkuAndIsFavorite(String sku, String userId) {
        Product product = this.productFeignClient.getProductBySkuAndIsFavorite(sku, userId);
        return this.toProductDto(product);
    }

    @Override
    public ProductDto getProductBySku(String sku) {
        Product product = this.productFeignClient.getProductBySku(sku);
        return this.toProductDto(product);
    }

    @Override
    public List<ProductDto> getProductsByLocationId(String id, int size) {
        List<Product> products = this.productFeignClient.getProductsByLocationId(id, size);
        List<ProductDto> productDtos = new ArrayList<>();
        products.forEach(product -> productDtos.add(this.toProductDto(product)));
        return productDtos;
    }

    @Override
    public List<ProductDto> getProductsByCategoryId(long id, int size) {
        List<Product> products = this.productFeignClient.getProductsByCategoryId(id, size);
        List<ProductDto> productDtos = new ArrayList<>();
        products.forEach(product -> productDtos.add(this.toProductDto(product)));
        return productDtos;
    }

    @Override
    public List<ProductDto> getProductsFavoritesList(String userId) {
        List<Product> products = this.productFeignClient.getProductsFavoritesList(userId);
        List<ProductDto> productDtos = new ArrayList<>();
        products.forEach(product -> productDtos.add(this.toProductDto(product)));
        return productDtos;
    }

    @Override
    public void addProductToFavorites(int id, String userId) {
        this.productFeignClient.addProductToFavorites(id, userId);
    }

    @Override
    public List<ProductDto> getProductsBySubCategoryId(int id, int size) {
        List<Product> products = this.productFeignClient.getProductsBySubCategoryId(id, size);
        List<ProductDto> productDtos = new ArrayList<>();
        products.forEach(product -> productDtos.add(this.toProductDto(product)));
        return productDtos;
    }

    @Override
    public List<ProductDto> getRandomProductsByLocationId(String id, int size) {
        List<Product> products = this.productFeignClient.getRandomProductsByLocationId(id, size);
        List<ProductDto> productDtos = new ArrayList<>();
        products.forEach(product -> productDtos.add(this.toProductDto(product)));
        return productDtos;
    }

    @Override
    public List<ProductDto> getRandomProductsByCategoryId(int id, int size) {
        List<Product> products = this.productFeignClient.getRandomProductsByCategoryId(id, size);
        List<ProductDto> productDtos = new ArrayList<>();
        products.forEach(product -> productDtos.add(this.toProductDto(product)));
        return productDtos;
    }

    @Override
    public List<ProductDto> getFilteredProducts(String word) {
        List<Product> products = this.productFeignClient.getFilteredProducts(word);
        List<ProductDto> productDtos = new ArrayList<>();
        products.forEach(product -> productDtos.add(this.toProductDto(product)));
        return productDtos;
    }

    @Override
    public void deleteProduct(int id) {
        this.productFeignClient.deleteProduct(id);
    }

    @Override
    public void editProduct(int id, Product product) {
        this.productFeignClient.editProduct(id, product);
    }

    @Override
    public List<ProductDto> getProductsWithLimit(int size) {
        List<Product> products = this.productFeignClient.getProductsWithLimit(size);
        List<ProductDto> productDtos = new ArrayList<>();
        products.forEach(product -> productDtos.add(this.toProductDto(product)));
        return productDtos;
    }

    @Override
    public List<ProductDto> getTopSellingProductsByLocationId(String luid) {
        List<Product> productList = this.productFeignClient.getProductsByLocationId(luid, 0);
        List<SellReport> reports = this.orderFeign.getTopOrders(luid);
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product : productList) {
            for (SellReport report : reports) {
                if (product.getProductSku().equalsIgnoreCase(report.getProductSku())) {
                    productDtos.add(this.toProductDto(product, report.getTotal()));
                }
            }
        }

        return productDtos.stream().sorted(Comparator.comparing(ProductDto::getTotalSell)).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductSoonOutOfStock(String luid) {
        List<Product> products = this.productFeignClient.getProductsByLocationId(luid, 0);
        List<ProductDto> productDtos = new ArrayList<>();
        products.forEach(product -> productDtos.add(this.toProductDto(product)));
        return productDtos
                .stream().filter((x) -> x.getQuantity() < 5)
                .collect(Collectors.toList());
    }

    @Override
    public int getProductsTotalCount(String luid) {
        return this.productFeignClient.getProductsCountByLocationId(luid).getValue();
    }

    private ProductDto toProductDto(Product product) {
        Location productLocation = this.locationFeign.getALocation(product.getLocationUniqueId());
        ProductAdditionalInformation additionalInformation = this.stockFeignClient.getASingleInfo(product.getProductSku());
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getWeight(),
                product.getSellingPrice(),
                product.isBookMarked(),
                product.getImageUrl(),
                product.getProductSku(),
                product.getLocationUniqueId(),
                additionalInformation.getQuantity(),
                additionalInformation.getArrivalDate(),
                additionalInformation.getBuyingPrice(),
                additionalInformation.getOriginLabel().getLabel(),
                0,
                productLocation.getName()
        );
    }

    private ProductDto toProductDto(Product product, int totalSell) {
        Location productLocation = this.locationFeign.getALocation(product.getLocationUniqueId());
        ProductAdditionalInformation additionalInformation = this.stockFeignClient.getASingleInfo(product.getProductSku());
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getWeight(),
                product.getSellingPrice(),
                product.isBookMarked(),
                product.getImageUrl(),
                product.getProductSku(),
                product.getLocationUniqueId(),
                additionalInformation.getQuantity(),
                additionalInformation.getArrivalDate(),
                additionalInformation.getBuyingPrice(),
                additionalInformation.getOriginLabel().getLabel(),
                totalSell,
                productLocation.getName()
        );
    }

    private String skuGenerator(String productName,
                                String originLabel,
                                String locationId) {
        String result;
        LocalDateTime date = LocalDateTime.now();
        int year = date.getYear();
        int hour = date.getHour();
        int min = date.getMinute();
        String locationIdFirst3Chars = this.getThreeFirstChars(locationId);
        String productFirst3Chars = this.getThreeFirstChars(productName);
        String originFirst3Chars = this.getThreeFirstChars(originLabel);
        result = locationIdFirst3Chars + productFirst3Chars + originFirst3Chars + year + hour + min;
        return result.toUpperCase();
    }

    private String getThreeFirstChars(String str) {
        return str.substring(0, Math.min(str.length(), 3));
    }
}
