package com.example.countryservice.controller;

import com.example.countryservice.model.Country;
import com.example.countryservice.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping("")
    public List<Country> getAllCountries() {
        return this.countryService.getAllCountries();
    }

    @PostMapping("/newCountry")
    public Country saveCountry(@RequestBody Country country) {
        return this.countryService.saveCountry(country);
    }

    @GetMapping("/getSingleCountry/{id}")
    public Country getASingleCountry(@PathVariable long id) {
        return this.countryService.getASingleCountry(id);
    }

    @DeleteMapping("/deleteCountry/{id}")
    public void deleteCountry(@PathVariable long id) {
        this.countryService.deleteCountry(id);
    }

    @PutMapping("/editCountry/{id}")
    public Country editCountry(@RequestBody Country newCountry, @PathVariable long id) {
        return this.countryService.editCountry(newCountry, id);
    }

}
