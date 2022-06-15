package com.example.roomservice.services;

import com.example.roomservice.model.Room;
import com.example.roomservice.model.RoomCategory;
import com.example.roomservice.model.RoomOverViewDto;
import com.example.roomservice.model.RoomResponse;
import com.example.roomservice.repositories.RoomCategoryRepository;
import com.example.roomservice.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {


    private final RoomRepository repository;

    private final RoomCategoryRepository categoryRepository;


    @Override
    public RoomResponse getSingleRoom(long id) {

        Optional<Room> roomOptional = this.repository.findById(id);

        if (roomOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room was not found");
        }

        Room room = roomOptional.get();

        return this.toRoomResponse(room);
    }

    @Override
    public Room editRoom(Room newRoom, long id) {
        Optional<Room> roomOptional = this.repository.findById(id);

        if (roomOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room was not found");
        }

        Room room = roomOptional.get();

        room.setRoomDescription(newRoom.getRoomDescription());
        room.setAvailableQuantity(newRoom.getAvailableQuantity());
        room.setPriceProNight(newRoom.getPriceProNight());

        return this.repository.save(room);
    }

    @Override
    public Room createRoom(Room newRoom) {

        Room createdRoom = new Room();

        Optional<RoomCategory> categoryOptional = this.categoryRepository.findByLabel(newRoom.getCategory().getLabel());

        if (categoryOptional.isEmpty()) {
            createdRoom = this.repository.save(newRoom);
        } else {
            createdRoom.setRoomDescription(newRoom.getRoomDescription());
            createdRoom.setLocationId(newRoom.getLocationId());
            createdRoom.setPriceProNight(newRoom.getPriceProNight());
            createdRoom.setAvailableQuantity(newRoom.getAvailableQuantity());
            createdRoom.setCategory(categoryOptional.get());
            this.repository.save(createdRoom);
        }

        return createdRoom;
    }

    @Override
    public List<RoomResponse> getAllRooms() {
        Iterable<Room> roomIterable;
        List<RoomResponse> rooms = new ArrayList<>();

        roomIterable = this.repository.findAll();

        roomIterable.forEach(room -> rooms.add(this.toRoomResponse(room)));

        return rooms;
    }

    @Override
    public RoomOverViewDto getRoomsByLocationId(long id) {
        List<RoomResponse> roomResponses = new ArrayList<>();
        List<Room> rooms = this.repository.findRoomsByLocationId(id);
        int total = 0;

        for (Room room : rooms) {
            total += room.getAvailableQuantity();
            roomResponses.add(this.toRoomResponse(room));
        }

        RoomOverViewDto roomOverViewDto = new RoomOverViewDto();
        roomOverViewDto.setRooms(roomResponses);
        roomOverViewDto.setTotalAvailableRooms(total);

        return roomOverViewDto;
    }

    @Override
    public void deleteRoom(long id) {
        Optional<Room> roomOptional = this.repository.findById(id);

        if (roomOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found");
        }

        Room existingRoom = roomOptional.get();

        this.repository.delete(existingRoom);
    }

    private RoomResponse toRoomResponse(Room room) {


        RoomResponse response = new RoomResponse();
        response.setDescription(room.getRoomDescription());
        response.setAvailableQuantity(room.getAvailableQuantity());
        response.setPriceProNight(room.getPriceProNight());
        response.setId(room.getId());
        response.setRoomCategory(room.getCategory());

        return response;
    }
}
