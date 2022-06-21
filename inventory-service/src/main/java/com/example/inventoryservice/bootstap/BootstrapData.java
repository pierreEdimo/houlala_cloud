package com.example.inventoryservice.bootstap;

import com.example.inventoryservice.model.ProductInformation;
import com.example.inventoryservice.repository.ProductInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final ProductInformationRepository repository;

    @Override
    public void run(String... args) throws Exception {

        ProductInformation info = new ProductInformation(
                "624601d2be57262127759a19",
                10,
                LocalDateTime.now(),
                123,
                "Cameroun"
        );

        ProductInformation info2 = new ProductInformation(
                "624605ffbe57262127759a2e",
                20,
                LocalDateTime.now(),
                222,
                "Cameroun"
        );

        this.repository.save(info);
        this.repository.save(info2);

    }
}
