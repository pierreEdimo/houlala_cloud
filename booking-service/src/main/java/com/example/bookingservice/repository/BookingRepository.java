package com.example.bookingservice.repository;

import com.example.bookingservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findBookingsByLocationId(long locationId);

    List<Booking> findBookingsByRenterId(String renterId);
}
