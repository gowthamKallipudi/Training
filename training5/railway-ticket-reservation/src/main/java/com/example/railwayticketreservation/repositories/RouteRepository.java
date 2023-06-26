package com.example.railwayticketreservation.repositories;

import com.example.railwayticketreservation.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface RouteRepository extends JpaRepository<Route, Integer> {

    @Query(value = "SELECT * FROM m_route WHERE id = ?1", nativeQuery = true)
    Route findTrainsByRoute(Integer routeId);

    @Query(value = "SELECT start_day_of_the_week FROM m_route WHERE train_id = ?1", nativeQuery = true)
    List<Integer> findStartDayByTrainId(Integer trainId);

    @Query(value = "SELECT id FROM m_route WHERE train_id = ?1 and start_day_of_the_week = ?2", nativeQuery = true)
    Integer findRouteIdByTrainIdAndStartDay(Integer trainId, Integer startDay);
}
