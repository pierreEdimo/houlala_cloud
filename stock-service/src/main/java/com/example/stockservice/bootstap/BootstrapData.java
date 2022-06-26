package com.example.stockservice.bootstap;

import com.example.stockservice.model.Origin;
import com.example.stockservice.model.ProductInformation;
import com.example.stockservice.repository.ProductInformationRepository;
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
                "anasku",
                10,
                LocalDate.now(),
                123,
                origin.getLabel(),
                "Mut"
        );

        ProductInformation info2 = new ProductInformation(
                "folesku",
                20,
                LocalDate.now(),
                222,
                origin1.getLabel(),
                "Mut"
        );

        this.repository.save(info);
        this.repository.save(info2);

    }
}
