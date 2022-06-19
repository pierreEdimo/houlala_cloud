package com.example.roomservice.bootstrap;

import com.example.roomservice.model.Room;
import com.example.roomservice.model.RoomCategory;
import com.example.roomservice.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final RoomRepository repository;


    @Override
    public void run(String... args) throws Exception {

        Room room = new Room("lorem ipsuum indolor in samor ino dolor",
                10,
                7500,
                new RoomCategory(
                        "Suite de Luxe"
                ),
                "Mut"
        );

        Room room2 = new Room(
                "lorem ipsuum indolor in samor ino dolor",
                10,
                12000,
                new RoomCategory(
                        "Studio"
                ),
                "Mut"
        );

        this.repository.save(room);
        this.repository.save(room2);
    }
}
