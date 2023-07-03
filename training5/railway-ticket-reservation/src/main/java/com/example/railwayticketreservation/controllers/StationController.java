package com.example.railwayticketreservation.controllers;

import com.example.railwayticketreservation.models.RouteDetails;
import com.example.railwayticketreservation.repositories.RouteDetailsRepository;
import com.example.railwayticketreservation.repositories.RouteRepository;
import com.example.railwayticketreservation.repositories.StationRepository;
import com.example.railwayticketreservation.repositories.TrainRepository;
import com.example.railwayticketreservation.utilModels.RouteDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin
public class StationController {

    @Autowired
    StationRepository stationRepository;

    @Autowired
    RouteDetailsRepository routeDetailsRepository;

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    RouteRepository routeRepository;

    @GetMapping("api/getAllStations")
    public ResponseEntity<List<String>> getAllStations() {
        return new ResponseEntity<>(stationRepository.findAllStations(), HttpStatus.OK);
    }

    @GetMapping("api/routeDetails")
    public ResponseEntity<List<RouteDetail>> routeDetails(@RequestParam(name = "day") Integer day, @RequestParam(name = "trainName") String trainName) {
        Integer trainId = trainRepository.findIdByName(trainName);
        Integer routeId = routeRepository.findRouteIdByTrainIdAndStartDay(trainId, day);
        List<RouteDetail> routeDetailList = new ArrayList<>();
        for(RouteDetails routeDetails: routeDetailsRepository.findByRouteId(routeId)) {
            RouteDetail routeDetail = new RouteDetail();
            routeDetail.setStation(routeDetails.getStation().getName());
            routeDetail.setHalt(routeDetails.getHaltInMinutes());
            routeDetailList.add(routeDetail);
        }
        return new ResponseEntity<>(routeDetailList, HttpStatus.OK);
    }
}
