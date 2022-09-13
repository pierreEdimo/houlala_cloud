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

        Origin origin3 = new Origin("Nigeria");

        Origin origin4 = new Origin("Ethiopia");

        ProductInformation info = new ProductInformation(
                "mutmaccam20222243",
                10,
                LocalDate.now(),
                123,
                origin.getLabel(),
                "Mut"
        );

        ProductInformation info2 = new ProductInformation(
                "mutmaccam20221911",
                20,
                LocalDate.now(),
                222,
                origin1.getLabel(),
                "Mut"
        );

        ProductInformation info3 = new ProductInformation(
                "mutmaccam2022334",
                20,
                LocalDate.now(),
                222,
                origin3.getLabel(),
                "Mut"
        );

        ProductInformation info4 = new ProductInformation(
                "mutharcam2022339",
                20,
                LocalDate.now(),
                222,
                origin4.getLabel(),
                "Mut"
        );

        this.repository.save(info);
        this.repository.save(info2);
        this.repository.save(info3);
        this.repository.save(info4);

    }
}
