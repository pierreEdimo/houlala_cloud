package com.example.bookingservice.service;

import com.example.bookingservice.exception.BookingServiceException;
import com.example.bookingservice.feign.BookingRoomFeignClient;
import com.example.bookingservice.model.Booking;
import com.example.bookingservice.model.BookingResponse;
import com.example.bookingservice.model.RoomResponse;
import com.example.bookingservice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repository;

    private final BookingRoomFeignClient feignClient;

    @Override
    public List<BookingResponse> getAllBookings() {
        Iterable<Booking> bookingIterable = this.repository.findAll();

        List<BookingResponse> bookings = new ArrayList<>();

        bookingIterable.forEach(booking -> bookings.add(this.toBookingResponse(booking)));

        return bookings;
    }

    @Override
    public BookingResponse getBooking(long id) {
        Optional<Booking> bookingOptional = this.repository.findById(id);

        if (bookingOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found");
        }

        return this.toBookingResponse(bookingOptional.get());
    }

    @Override
    public Booking addNewBooking(Booking newBooking) {
        return this.repository.save(newBooking);
    }

    @Override
    public void deleteBooking(long id) {
        Optional<Booking> bookingOptional = this.repository.findById(id);

        if (bookingOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found");
        }

        this.repository.delete(bookingOptional.get());
    }

    @Override
    public List<BookingResponse> getBookingsByLocationId(long locationId) {
        List<Booking> bookingList = this.repository.findBookingsByLocationId(locationId);
        List<BookingResponse> bookingFromLocationIdList = new ArrayList<>();

        bookingList.forEach(booking -> bookingFromLocationIdList.add(this.toBookingResponse(booking)));

        return bookingFromLocationIdList;
    }

    @Override
    public List<BookingResponse> getBookingsByRenterId(String renterId) {
        List<Booking> bookingList = this.repository.findBookingsByRenterId(renterId);
        List<BookingResponse> bookingListFromRenter = new ArrayList<>();

        bookingList.forEach(booking -> bookingListFromRenter.add(this.toBookingResponse(booking)));

        return bookingListFromRenter;
    }

    private BookingResponse toBookingResponse(Booking booking) {
        BookingResponse response = new BookingResponse();

        response.setArrival(booking.getArrival());
        response.setDeparture(booking.getDeparture());
        response.setTotalRoomQuantity(booking.getTotalRoomQuantity());
        response.setTotalPrice(booking.getTotalPrice());

        return response;
    }
}
