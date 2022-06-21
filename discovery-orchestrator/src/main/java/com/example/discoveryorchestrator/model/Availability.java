package com.example.discoveryorchestrator.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
public class Availability {

    private String date;

    private Time opening;

    private Time closing;
}
