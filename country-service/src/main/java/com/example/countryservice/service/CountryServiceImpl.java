package com.example.countryservice.service;

import com.example.countryservice.model.Country;
import com.example.countryservice.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public Country getASingleCountry(long id) {
        Optional<Country> countryOptional = this.countryRepository.findById(id);

        if (countryOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found");
        }

        return countryOptional.get();
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
        Optional<Country> country = this.countryRepository.findById(id);

        if(country.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found");
        }

        Country existingCountry = country.get();

        this.countryRepository.delete(existingCountry);
    }

    @Override
    public Country editCountry(Country newCountry, long id) {

        Optional<Country> country = this.countryRepository.findById(id);

        if(country.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found");
        }

        Country existingCountry = country.get();
        existingCountry.setCode(newCountry.getCode());
        existingCountry.setName(newCountry.getName());

        return this.countryRepository.save(existingCountry);
    }
}
