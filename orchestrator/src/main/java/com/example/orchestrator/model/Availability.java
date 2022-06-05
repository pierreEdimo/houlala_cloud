package com.example.orchestrator.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Availability {

    private String date;

    private LocalDate opening;

    private LocalDate closing;
}
