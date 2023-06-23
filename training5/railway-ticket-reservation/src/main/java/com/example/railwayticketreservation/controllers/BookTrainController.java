package com.example.railwayticketreservation.controllers;

import com.example.railwayticketreservation.models.Train;
import com.example.railwayticketreservation.models.TrainCoach;
import com.example.railwayticketreservation.repositories.BookingsRepository;
import com.example.railwayticketreservation.repositories.TrainCoachRespository;
import com.example.railwayticketreservation.repositories.TrainRepository;
import com.example.railwayticketreservation.utilModels.TrainAvailable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
@CrossOrigin
public class BookTrainController {

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    BookingsRepository bookingsRepository;

    @Autowired
    TrainCoachRespository trainCoachRespository;

    @GetMapping("/api/availableTrains")
    public ResponseEntity<List<TrainAvailable>> availableTrains() {
        List<TrainAvailable> trainsAvailable = new ArrayList<>();
        List<Train> trains = trainRepository.findAll();
        for(Train train : trains) {
            TrainAvailable trainAvailable = new TrainAvailable();
            trainAvailable.setTrainName(train.getName());
            Map<String, Integer> map = new HashMap<>();
            List<TrainCoach> coaches = trainCoachRespository.findAll();
            int noSl = coaches.get(0).getNoOfSeats() - bookingsRepository.findByCoach("SL", 1);
            int noA1 = coaches.get(1).getNoOfSeats() - bookingsRepository.findByCoach("A1", 1);
            int noA2 = coaches.get(2).getNoOfSeats() - bookingsRepository.findByCoach("A2", 1);
            int noA3 = coaches.get(3).getNoOfSeats() - bookingsRepository.findByCoach("A3", 1);
            trainAvailable.setSlCount(noSl);
            trainAvailable.setA1Count(noA1);
            trainAvailable.setA2Count(noA2);
            trainAvailable.setA3Count(noA3);
            trainsAvailable.add(trainAvailable);
        }
        return new ResponseEntity<>(trainsAvailable, HttpStatus.OK);
    }
}
