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

    @GetMapping("api/getCancelledSeats")
    public ResponseEntity<Map<String, Integer>> getCancelledSeats(@RequestParam(name = "name") String name, @RequestParam(name = "date") LocalDate date, @RequestParam(name = "day") Integer day) {
        Integer trainId = trainRepository.findIdByName(name);
        Integer routeId = routeRepository.findRouteIdByTrainIdAndStartDay(trainId, day);
        List<TrainCoach> trainCoachList = trainCoachRepository.findAll();
        System.out.println(routeId);
        Map<String, Integer> coaches = new HashMap<>();
        for(TrainCoach trainCoach : trainCoachList) {
            coaches.put(trainCoach.getCoach(), bookingsRepository.getBookingsByStatus(trainCoach.getCoach(), routeId, date, "Cancelled"));
        }
        return new ResponseEntity<>(coaches, HttpStatus.OK);
    }

    @GetMapping("/api/availableTrainSeats")
    public ResponseEntity<Map<String, Integer>> availableTrainSeats(@RequestParam(name = "name") String name, @RequestParam(name = "date") LocalDate date, @RequestParam(name = "day") Integer day) {
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
        return new ResponseEntity<>(coaches, HttpStatus.OK);
    }

    @GetMapping("api/getTrainBetween")
    public ResponseEntity<Map<String, List<Integer>>> getTrainBetween(@RequestParam(name = "source") String sourceStation, @RequestParam(name = "destination") String destinationStation, @RequestParam(name = "day") Integer day) {
        List<Integer> routeIds1 = routeDetailsRepository.findAllRoutesWithStationId(stationRepository.findByStation(sourceStation).getId());
        List<Integer> routeIds2 = routeDetailsRepository.findAllRoutesWithStationId(stationRepository.findByStation(destinationStation).getId());
        routeIds1.retainAll(routeIds2);
        HashSet<Integer> trainIds = new HashSet<>();
        for(Integer routeId : routeIds1) {
            Route route = routeRepository.findTrainsByRoute(routeId);
            Integer routeSeq1 = routeDetailsRepository.findSeqNoByRouteIdStationId(routeId, stationRepository.findByStation(sourceStation).getId());
            Integer routeSeq2 = routeDetailsRepository.findSeqNoByRouteIdStationId(routeId, stationRepository.findByStation(destinationStation).getId());
            if(routeSeq2 > routeSeq1) {
                trainIds.add(route.getTrain().getId());
            }
        }
        Map<String, List<Integer>> data = new HashMap<>();
        for(Integer train : trainIds) {
            List<Integer> days = routeRepository.findStartDayByTrainId(train);
            if(days.contains(day))
                data.put(trainRepository.findNameById(train), days);
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    
}
