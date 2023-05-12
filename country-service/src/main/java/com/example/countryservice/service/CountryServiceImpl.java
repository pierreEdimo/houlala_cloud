package com.example.countryservice.service;

import com.example.countryservice.model.Country;
import com.example.countryservice.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public Country getASingleCountry(long id) {
        return this.countryRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));
    }

    @Override
    public List<Country> getAllCountries() {
        List<Country> countries = new ArrayList<>();
        Iterable<Country> countriesIterable;
        countriesIterable = this.countryRepository.findAll();
        countriesIterable.forEach(countries::add);
        return countries;

    }

    @Override
    public Country saveCountry(Country newCountry) {
        return this.countryRepository.save(newCountry);
    }

    @Override
    public void deleteCountry(long id) {
        Country existingCountry = this.countryRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));
        this.countryRepository.delete(existingCountry);
    }

    @Override
    public Country editCountry(Country newCountry, long id) {
        Country existingCountry = this.countryRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));
        existingCountry.setCode(newCountry.getCode());
        existingCountry.setName(newCountry.getName());
        existingCountry.setCurrency(newCountry.getCurrency());
        return this.countryRepository.save(existingCountry);
    }
}
