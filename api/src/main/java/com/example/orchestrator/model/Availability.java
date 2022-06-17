package com.example.orchestrator.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;

@Getter
@Setter
public class Availability {

    private String date;

    private Time opening;

    private Time closing;
}
