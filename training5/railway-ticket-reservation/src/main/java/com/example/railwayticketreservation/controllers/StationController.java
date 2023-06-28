package com.example.railwayticketreservation.controllers;

import com.example.railwayticketreservation.repositories.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin
public class StationController {

    @Autowired
    StationRepository stationRepository;

    @GetMapping("api/getAllStations")
    public ResponseEntity<List<String>> getAllStations() {
        return new ResponseEntity<>(stationRepository.findAllStations(), HttpStatus.OK);
    }
}
