package com.example.countryservice.repository;

import com.example.countryservice.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> { }
