package com.example.railwayticketreservation.repositories;

import com.example.railwayticketreservation.models.RouteDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RouteDetailsRepository extends JpaRepository<RouteDetails, Integer> {

    @Query(value = "SELECT * FROM m_route_details WHERE station_id = ?1", nativeQuery = true)
    List<RouteDetails> findAllRoutesWithStationId(Integer stationId);
}
