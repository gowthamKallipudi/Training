package com.example.railwayticketreservation.controllers;

import com.example.railwayticketreservation.models.*;
import com.example.railwayticketreservation.repositories.*;
import com.example.railwayticketreservation.utilModels.TrainAvailable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @Autowired
    StationRepository stationRepository;

    @Autowired
    RouteDetailsRepository routeDetailsRepository;

    @Autowired
    RouteRepository routeRepository;

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

    @GetMapping("api/getTrainBetween")
    public ResponseEntity<List<String>> getTrainBetween(@RequestParam(name = "source") String sourceStation, @RequestParam(name = "destination") String destinationStation) {
        Integer station1 = stationRepository.findByStation(sourceStation).getId();
        Integer station2 = stationRepository.findByStation(destinationStation).getId();
        System.out.println(station1 + " " + station2);
        List<Integer> routeIds1 = new ArrayList<>();
        List<Integer> routeIds2 = new ArrayList<>();
        List<RouteDetails> routeDetails1 = routeDetailsRepository.findAllRoutesWithStationId(station1);
        List<RouteDetails> routeDetails2 = routeDetailsRepository.findAllRoutesWithStationId(station2);
        for(RouteDetails routeDetails : routeDetails1) {
            routeIds1.add(routeDetails.getRoute().getId());
        }
        for(RouteDetails routeDetails : routeDetails2) {
            routeIds2.add(routeDetails.getRoute().getId());
        }
        routeIds1.retainAll(routeIds2);
        List<String> trainNames = new ArrayList<>();
        for(Integer routeId : routeIds1) {
            Route route = routeRepository.findTrainsByRoute(routeId);
            trainNames.add(route.getTrain().getName());
        }
        for(String names : trainNames) {
            System.out.println(names);
        }
        return new ResponseEntity<>(trainNames, HttpStatus.OK);
    }
}
