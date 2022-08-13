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
    public void run(String... args) throws Exception {

        Address address = new Address(
                "mustercity",
                "musterallee",
                "00000"
        );

        Address address2 = new Address(
                "mustercity",
                "badmusterallee",
                "000000"
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

        Availability monday2 = new Availability(
                "Monday",
                LocalTime.of(8, 0),
                LocalTime.of(15, 0)
        );

        Availability tuesday2 = new Availability(
                "Tuesday",
                LocalTime.of(8, 0),
                LocalTime.of(15, 0)
        );

        Availability wednesday2 = new Availability(
                "Wednesday",
                LocalTime.of(8, 0),
                LocalTime.of(15, 0)
        );

        Availability thursday2 = new Availability(
                "Thursday",
                LocalTime.of(8, 0),
                LocalTime.of(15, 0)
        );

        Availability friday2 = new Availability(
                "Friday",
                LocalTime.of(8, 0),
                LocalTime.of(15, 0)
        );


        List<Availability> availabilityList = new ArrayList<>();
        availabilityList.add(monday);
        availabilityList.add(tuesday);
        availabilityList.add(wednesday);
        availabilityList.add(thursday);
        availabilityList.add(friday);

        List<Availability> availabilityList2 = new ArrayList<>();
        availabilityList2.add(monday2);
        availabilityList2.add(tuesday2);
        availabilityList2.add(wednesday2);
        availabilityList2.add(thursday2);
        availabilityList2.add(friday2);

        Location location = new Location(
                "MusterRestaurant",
                "lorem ipsum in dolor in somor",
                1L,
                2L,
                address,
                "12345678",
                "muster@gmail.com",
                "www.musterweb.com",
                "mu",
                "https://images.unsplash.com/photo-1556155304-28f97c2c4c62?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80",
                "pedimonka"

        );

        location.setAvailabilityList(availabilityList);

        Location location2 = new Location(
                "Houlala",
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt" +
                        " ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores " +
                        "et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
                1L,
                3L,
                address2,
                "12345678",
                "muster@gmail.com",
                "www.musterweb.com",
                "Mut",
                "https://images.unsplash.com/photo-1556155304-28f97c2c4c62?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80",
                "pedimonkoe"
        );

        location2.setAvailabilityList(availabilityList2);
        location2.setStore(true);


        this.repository.save(location);
        this.repository.save(location2);

    }
}
