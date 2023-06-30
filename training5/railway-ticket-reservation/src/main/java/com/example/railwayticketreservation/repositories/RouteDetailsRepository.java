package com.example.railwayticketreservation.repositories;

import com.example.railwayticketreservation.models.RouteDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RouteDetailsRepository extends JpaRepository<RouteDetails, Integer> {

    @Query(value = "SELECT route_id FROM m_route_details WHERE station_id = ?1", nativeQuery = true)
    List<Integer> findAllRoutesWithStationId(Integer stationId);

    @Query(value = "SELECT seq_no FROM m_route_details WHERE route_id = ?1 AND station_id = ?2", nativeQuery = true)
    Integer findSeqNoByRouteIdStationId(Integer routeId, Integer stationId);
}
