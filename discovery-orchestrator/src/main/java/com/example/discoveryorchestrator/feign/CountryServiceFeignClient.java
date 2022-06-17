package com.example.discoveryorchestrator.feign;

import com.example.discoveryorchestrator.exception.OrchestratorException;
import com.example.discoveryorchestrator.model.Country;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "country", path = "country")
public interface CountryServiceFeignClient {

    @GetMapping("")
    List<Country> get() throws OrchestratorException;

    @PostMapping("/newCountry")
    Country createCountry(@RequestBody Country newCountry) throws OrchestratorException;

    @GetMapping("/getSingleCountry/{id}")
    Country getASingleCountry(@PathVariable long id) throws OrchestratorException;

    @DeleteMapping("/deleteCountry/{id}")
    void deleteCountry(@PathVariable long id) throws OrchestratorException;

    @PutMapping("/editCountry/{id}")
    Country editCountry(@RequestBody Country newCountry, @PathVariable long id) throws OrchestratorException;

}
