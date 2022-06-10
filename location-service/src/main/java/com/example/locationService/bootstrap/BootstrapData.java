package com.example.locationService.bootstrap;

import com.example.locationService.model.Address;
import com.example.locationService.model.Availability;
import com.example.locationService.model.Location;
import com.example.locationService.repositories.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final LocationRepository repository;

    @Override
    public void run(String... args) {

        Address address = new Address(
                "mustercity",
                "musterallee",
                "00000"
        );

        Availability monday = new Availability(
                "Monday",
                LocalTime.of(9, 0),
                LocalTime.of(18, 0)
        );

        Availability tuesday = new Availability(
                "Tuesday",
                LocalTime.of(9, 0),
                LocalTime.of(18, 0)
        );

        Availability wednesday = new Availability(
                "Wednesday",
                LocalTime.of(9, 0),
                LocalTime.of(18, 0)
        );

        Availability thursday = new Availability(
                "Thursday",
                LocalTime.of(9, 0),
                LocalTime.of(18, 0)
        );

        Availability friday = new Availability(
                "Friday",
                LocalTime.of(9, 0),
                LocalTime.of(18, 0)
        );


        List<Availability> availabilityList = new ArrayList<>();
        availabilityList.add(monday);
        availabilityList.add(tuesday);
        availabilityList.add(wednesday);
        availabilityList.add(thursday);
        availabilityList.add(friday);

        Location location = new Location(
                "Musterhotel",
                "lorem ipsum in dolor in somor",
                1L,
                1L,
                address,
                "12345678",
                "muster@gmail.com",
                "www.musterweb.com"

        );

        location.setAvailabilityList(availabilityList);

        this.repository.save(location);

    }
}
