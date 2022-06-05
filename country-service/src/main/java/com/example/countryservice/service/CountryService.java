package com.example.countryservice.service;

import com.example.countryservice.model.Country;

import java.util.List;

public interface CountryService {

    Country getASingleCountry(long id);

    List<Country> getAllCountries();

    Country saveCountry(Country newCountry);

    void deleteCountry(long id);

    Country editCountry(Country newCountry, long id);
}
