package com.example.railwayticketreservation.repositories;

import com.example.railwayticketreservation.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface RouteRepository extends JpaRepository<Route, Integer> {

    @Query(value = "SELECT * FROM m_route WHERE id = ?1", nativeQuery = true)
    Route findTrainsByRoute(Integer routeId);
}
