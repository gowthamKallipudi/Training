package com.example.railwayticketreservation.controllers;

import com.example.railwayticketreservation.models.Bookings;
import com.example.railwayticketreservation.models.Register;
import com.example.railwayticketreservation.models.Route;
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

    @Autowired
    StationRepository stationRepository;

    @GetMapping("api/getBookings/{user}")
    public ResponseEntity<List<Booking>> getBookings(@PathVariable("user") Integer userId) {
        List<Bookings> bookings = bookingsRepository.findAllByUserId(userId);
        List<Booking> bookings1 = new ArrayList<>();
        for(Bookings booking1 : bookings) {
            Booking booking = new Booking();
            booking.setBookingId(booking1.getBookingId());
            booking.setUserName(booking1.getUser().getLastName());
            booking.setTrainName(booking1.getRoute().getTrain().getName());
            booking.setDate(booking1.getDate());
            booking.setCoach(booking1.getCoach());
            booking.setSeatNo(booking1.getSeatNo());
            booking.setSource(booking1.getStationS().getName());
            booking.setDestination(booking1.getStationD().getName());
            bookings1.add(booking);
        }
        return new ResponseEntity<>(bookings1, HttpStatus.OK);
    }

    @PostMapping("/api/addBooking")
    public ResponseEntity<String> addBooking(@RequestBody BookingDetails bookingDetails) {
        Bookings bookings = new Bookings();
        bookings.setDate(bookingDetails.getDate());
        Integer trainId = trainRepository.findIdByName(bookingDetails.getTrainName());
        Integer routeId = routeRepository.findRouteIdByTrainIdAndStartDay(trainId, bookingDetails.getDay());
        Route route = routeRepository.findById(routeId).get();
        bookings.setRoute(route);
        Register register = registerRepository.findById(bookingDetails.getUserId()).get();
        bookings.setUser(register);
        bookings.setStationS(stationRepository.findByStation(bookingDetails.getSource()));
        bookings.setStationD(stationRepository.findByStation(bookingDetails.getDestination()));
        LocalDate date = bookingDetails.getDate();
        Integer total = trainCoachRepository.findByCoach(bookingDetails.getCoach()).getNoOfSeats();
        Integer booked = bookingsRepository.findByCoach(bookingDetails.getCoach(), routeId, date);
        Integer count = null;
        switch(bookingDetails.getCoach()) {
            case "SL":
                count = trainRepository.findById(trainId).get().getNoSl();
                break;
            case "A3":
                count = trainRepository.findById(trainId).get().getNo3rdAc();
                break;
            case "A2":
                count = trainRepository.findById(trainId).get().getNo2ndAc();
                break;
            case "A1":
                count = trainRepository.findById(trainId).get().getNo1stAc();
                break;
        }
        System.out.println(count);
        System.out.println(total);
        System.out.println(booked);
        int currentBookingNo = booked + 1;
        int helper = (total / 6);
        int a = 0, b = 0;
        if(currentBookingNo % helper == 0) {
            a = (currentBookingNo - 1) / helper;
            b = (currentBookingNo - 1) % helper + 1;
        }
        else {
            a = currentBookingNo / helper;
            b = currentBookingNo % helper;
        }
        int c = 0, d = a / count;
        if(a % count == 0) {
            c = a % count + 1;
        }else {
            c = (a + 1) % count;
        }
        int currentCoachSeat = helper * d + b;
        int e = 0;
        int f = 0;
        if(currentCoachSeat % 6 == 0) {
            e = (currentCoachSeat - 1) / 6;
            f = (currentCoachSeat - 1) % 6 + 1;
        }
        else {
            e = currentCoachSeat / 6;
            f = currentCoachSeat % 6;
        }
        int g;
        int h = e / 2;
        if(e % 2 == 0) {
             g = total / 2 - 6 * h - f + 1;
        }else {
             g = total / 2 + 6 * h + f;
        }
        String res;
        if(c == 0)
            res = bookingDetails.getCoach() + (count);
        else
            res = bookingDetails.getCoach() + (c);
        bookings.setCoach(res);

        if(total * count - booked > 0) {
            bookings.setSeatNo(g);
            bookingsRepository.save(bookings);
            return new ResponseEntity<>("Created SuccessFully", HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
