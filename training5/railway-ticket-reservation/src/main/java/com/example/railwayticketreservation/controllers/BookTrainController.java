package com.example.railwayticketreservation.controllers;

import com.example.railwayticketreservation.models.*;
import com.example.railwayticketreservation.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    TrainCoachRepository trainCoachRepository;

    @Autowired
    StationRepository stationRepository;

    @Autowired
    RouteDetailsRepository routeDetailsRepository;

    @Autowired
    RouteRepository routeRepository;

    @GetMapping("/api/availableTrains")
    public ResponseEntity<Map<String, Integer>> availableTrains(@RequestParam(name = "name") String name, @RequestParam(name = "date") LocalDate date, @RequestParam(name = "day") Integer day) {
        Integer trainId = trainRepository.findIdByName(name);
        Integer routeId = routeRepository.findRouteIdByTrainIdAndStartDay(trainId, day);
        List<TrainCoach> trainCoachList = trainCoachRepository.findAll();
        Map<String, Integer> coaches = new HashMap<>();
        Integer count = null;
        for(TrainCoach trainCoach : trainCoachList) {
            switch(trainCoach.getCoach()) {
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
            coaches.put(trainCoach.getCoach(), trainCoach.getNoOfSeats() * count - bookingsRepository.findByCoach(trainCoach.getCoach(), routeId, date));
        }
        return new ResponseEntity<Map<String, Integer>>(coaches, HttpStatus.OK);
    }

    @GetMapping("api/getTrainBetween")
    public ResponseEntity<Map<String, List<Integer>>> getTrainBetween(@RequestParam(name = "source") String sourceStation, @RequestParam(name = "destination") String destinationStation, @RequestParam(name = "day") Integer day) {
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
        HashSet<Integer> trainIds = new HashSet<>();
        for(Integer routeId : routeIds1) {
            Route route = routeRepository.findTrainsByRoute(routeId);
            trainIds.add(route.getTrain().getId());
            trainNames.add(route.getTrain().getName());
        }
        System.out.println(trainIds);
        Map<String, List<Integer>> data = new HashMap<>();
        for(Integer train : trainIds) {
            List<Integer> days = routeRepository.findStartDayByTrainId(train);
            if(days.contains(day))
                data.put(trainRepository.findNameById(train), days);
            System.out.println(routeRepository.findStartDayByTrainId(train));
        }
        System.out.println(data);
        HashSet<String> trains = new HashSet<>(trainNames);
        for(String names : trains) {
            System.out.println(names);
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("api/getAvailableDays")
    public ResponseEntity<List<Integer>> getAvailableDays(@RequestParam(name = "trainName") String name) {
        Integer trainId = trainRepository.findIdByName(name);
        return new ResponseEntity<>(routeRepository.findStartDayByTrainId(trainId), HttpStatus.OK);
    }
}
