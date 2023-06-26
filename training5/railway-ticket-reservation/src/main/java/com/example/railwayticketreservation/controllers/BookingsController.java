package com.example.railwayticketreservation.controllers;

import com.example.railwayticketreservation.models.Bookings;
import com.example.railwayticketreservation.models.Register;
import com.example.railwayticketreservation.models.Route;
import com.example.railwayticketreservation.models.TrainCoach;
import com.example.railwayticketreservation.repositories.*;
import com.example.railwayticketreservation.utilModels.Booking;
import com.example.railwayticketreservation.utilModels.BookingDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin
public class BookingsController {

    @Autowired
    BookingsRepository bookingsRepository;

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    TrainCoachRepository trainCoachRepository;

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

    @PostMapping("/api/addBooking")
    public ResponseEntity<String> addBooking(@RequestBody BookingDetails bookingDetails) {
        Bookings bookings = new Bookings();
        bookings.setCoach(bookingDetails.getCoach());
        bookings.setDate(bookingDetails.getDate());
        Integer trainId = trainRepository.findIdByName(bookingDetails.getTrainName());
        Integer routeId = routeRepository.findRouteIdByTrainIdAndStartDay(trainId, bookingDetails.getDay());
        Route route = routeRepository.findById(routeId).get();
        bookings.setRoute(route);
        Register register = registerRepository.findById(bookingDetails.getUserId()).get();
        bookings.setUser(register);
        LocalDate date = bookingDetails.getDate();
        List<TrainCoach> trainCoachList = trainCoachRepository.findAll();
        int booked = 0, total = 0;
        for(TrainCoach trainCoach : trainCoachList) {
           if(trainCoach.getCoach().compareTo(bookingDetails.getCoach()) == 0) {
               total = trainCoach.getNoOfSeats();
               booked = bookingsRepository.findByCoach(trainCoach.getCoach(), routeId, date);
           }
        }
        if(total - booked > 0) {
            bookings.setSeatNo(booked + 1);
            bookingsRepository.save(bookings);
            return new ResponseEntity<>("Created SuccessFully", HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
