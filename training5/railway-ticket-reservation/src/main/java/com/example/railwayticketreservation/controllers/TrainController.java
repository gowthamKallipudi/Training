package com.example.railwayticketreservation.controllers;

import com.example.railwayticketreservation.models.Route;
import com.example.railwayticketreservation.models.RouteDetails;
import com.example.railwayticketreservation.models.Station;
import com.example.railwayticketreservation.models.Train;
import com.example.railwayticketreservation.repositories.RouteDetailsRepository;
import com.example.railwayticketreservation.repositories.RouteRepository;
import com.example.railwayticketreservation.repositories.StationRepository;
import com.example.railwayticketreservation.repositories.TrainRepository;
import com.example.railwayticketreservation.utilModels.TrainUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
@CrossOrigin
public class TrainController {

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    StationRepository stationRepository;

    @Autowired
    RouteDetailsRepository routeDetailsRepository;

    @PostMapping("api/addTrain")
    public ResponseEntity<String> addTrain(@RequestBody TrainUtil trainUtil) {
        try {
            Train train = new Train();
            train.setName(trainUtil.getTrainName());
            train.setNo1stAc(trainUtil.getNo1stAc());
            train.setNo2ndAc(trainUtil.getNo2ndAc());
            train.setNo3rdAc(trainUtil.getNo3rdAc());
            train.setNoSl(trainUtil.getNoSl());
            Train train1 = trainRepository.save(train);
            List<Station> stations = new ArrayList<>();
            for (String station : trainUtil.getStations()) {
                Optional<Station> station1 = stationRepository.findByStationName(station);
                if (station1.isEmpty()) {
                    Station station2 = new Station();
                    station2.setName(station);
                    stations.add(stationRepository.save(station2));
                } else {
                    stations.add(station1.get());
                }
            }
            for (Integer startDay : trainUtil.getStartDays()) {
                Route route = new Route();
                route.setTrain(train1);
                route.setStartDayOfTheWeek(startDay);
                Route route1 = routeRepository.save(route);
                List<Integer> halts = trainUtil.getHalts();
                for (int index = 0; index < stations.size(); index++) {
                    RouteDetails routeDetails = new RouteDetails();
                    Station station = stations.get(index);
                    Integer halt = halts.get(index);
                    routeDetails.setRoute(route1);
                    routeDetails.setHaltInMinutes(halt);
                    routeDetails.setStation(station);
                    routeDetailsRepository.save(routeDetails);
                }
            }
            return new ResponseEntity<>("Added Successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Not Added Successfully" ,HttpStatus.BAD_REQUEST);
        }
    }
}
