package com.example.railwayticketreservation.bookings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin
public class BookingsController {

    @Autowired
    BookingsRepository bookingsRepository;


    @PostMapping("/api/addBooking")
    public ResponseEntity<BookingsModel> saveBooking(@RequestBody BookingsModel bookingsModel) {
        return new ResponseEntity<>(bookingsRepository.save(bookingsModel), HttpStatus.CREATED);
    }

    @GetMapping("/api/getAllBookings")
    public ResponseEntity<List<BookingsModel>> getAllBookings() {
        List<BookingsModel> bookings = bookingsRepository.findAll();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

}
