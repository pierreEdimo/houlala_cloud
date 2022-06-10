package com.example.countryservice.bootstrap;

import com.example.countryservice.model.Country;
import com.example.countryservice.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final CountryRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Country country = new Country(
                "Cameroun",
                "CMR"
        );

        Country country1 = new Country(
                "Nigeria",
                "NGA"
        );

        this.repository.save(country);
        this.repository.save(country1);
    }
}
