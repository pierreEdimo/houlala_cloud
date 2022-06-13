package com.example.bookingservice.service;

import com.example.bookingservice.model.Booking;
import com.example.bookingservice.model.BookingResponse;

import java.util.List;

public interface BookingService {
    List<BookingResponse> getAllBookings();

    BookingResponse getBooking(long id);

    Booking addNewBooking(Booking newBooking);

    void deleteBooking(long id);

    List<BookingResponse> getBookingsByLocationId(long locationId);

    List<BookingResponse> getBookingsByRenterId(String renterId);
}
