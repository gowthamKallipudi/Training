package com.example.railwayticketreservation.controllers;

import com.example.railwayticketreservation.models.Bookings;
import com.example.railwayticketreservation.repositories.BookingsRepository;
import com.example.railwayticketreservation.utilModels.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin
public class BookingsController {

    @Autowired
    BookingsRepository bookingsRepository;

    @GetMapping("api/getBookings/{user}")
    public ResponseEntity<List<Booking>> getBookings(@PathVariable("user") Integer userId) {
        List<Bookings> bookings = bookingsRepository.findAllByUserId(userId);
        List<Booking> bookings1 = new ArrayList<>();
        for(Bookings booking1 : bookings) {
            Booking booking = new Booking();
            booking.setBookingId(booking1.getBookingId());
            booking.setUserId(booking1.getUser().getId());
            booking.setRouteId(booking1.getRoute().getId());
            booking.setDate(booking1.getDate());
            booking.setCoach(booking1.getCoach());
            booking.setSeatNo(booking1.getSeatNo());
            bookings1.add(booking);
        }
        return new ResponseEntity<>(bookings1, HttpStatus.OK);
    }
}
