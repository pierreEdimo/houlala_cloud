package com.example.inventoryservice.bootstap;

import com.example.inventoryservice.model.Origin;
import com.example.inventoryservice.model.ProductInformation;
import com.example.inventoryservice.repository.ProductInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final ProductInformationRepository repository;

    @Override
    public void run(String... args) throws Exception {

        Origin origin  = new Origin("Cameroun");

        Origin origin1 = new Origin("Ghana");

        ProductInformation info = new ProductInformation(
                "624601d2be57262127759a19",
                10,
                LocalDate.now(),
                123,
                origin.getLabel()
        );

        ProductInformation info2 = new ProductInformation(
                "624605ffbe57262127759a2e",
                20,
                LocalDate.now(),
                222,
                origin1.getLabel()
        );

        this.repository.save(info);
        this.repository.save(info2);

    }
}
