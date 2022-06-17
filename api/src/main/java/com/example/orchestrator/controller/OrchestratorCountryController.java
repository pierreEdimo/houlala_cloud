package com.example.orchestrator.controller;

import com.example.orchestrator.exception.OrchestratorException;
import com.example.orchestrator.feign.CountryServiceFeignClient;
import com.example.orchestrator.model.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/countries")
public class OrchestratorCountryController {

    private final CountryServiceFeignClient countryServiceFeignClient;

    @GetMapping("")
    public List<Country> getHello() {
        try {
            return this.countryServiceFeignClient.get();
        } catch (OrchestratorException ex) {
            throw new ResponseStatusException(ex.getHttpStatus(), ex.getMessage());
        }
    }

    @PostMapping("/newCountry")
    @ResponseStatus(HttpStatus.CREATED)
    public Country createCountry(@RequestBody Country newCountry) {
        try {
            return this.countryServiceFeignClient.createCountry(newCountry);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/getSingleCountry/{id}")
    public Country getASingleCountry(@PathVariable long id) {
        try {
            return this.countryServiceFeignClient.getASingleCountry(id);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @DeleteMapping("/deleteCountry/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCountry(@PathVariable long id) {
        try {
            this.countryServiceFeignClient.deleteCountry(id);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PutMapping("/editCountry/{id}")
    public Country editCountry(@RequestBody Country newCountry, @PathVariable long id) {
        try {
            return this.countryServiceFeignClient.editCountry(newCountry, id);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }
}
