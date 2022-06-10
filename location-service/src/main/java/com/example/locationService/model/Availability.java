package com.example.locationService.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;

    private Time opening;

    private Time closing;

    public Availability() {
    }

    public Availability(String date, LocalTime opening, LocalTime closing) {
        this.date = date;
        this.opening = Time.valueOf(opening);
        this.closing = Time.valueOf(closing);
    }
}
