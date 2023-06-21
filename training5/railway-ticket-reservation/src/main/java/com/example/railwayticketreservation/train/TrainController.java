package com.example.railwayticketreservation.train;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin
public class TrainController {

    @Autowired
    TrainRepository trainRepository;

    @PostMapping("/api/addTrain")
    public ResponseEntity<TrainModel> saveTrain(@RequestBody TrainModel trainModel) {
        return new ResponseEntity<>(trainRepository.save(trainModel), HttpStatus.CREATED);
    }

    @GetMapping("/api/getAllTrains")
    public ResponseEntity<List<TrainModel>> getAllTrains() {
        List<TrainModel> trains = trainRepository.findAll();
        return new ResponseEntity<>(trains, HttpStatus.OK);
    }
}
