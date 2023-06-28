package com.example.railwayticketreservation.repositories;

import com.example.railwayticketreservation.models.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, Integer> {
    @Query(value = "SELECT * FROM m_station WHERE name = ?1", nativeQuery = true)
    Station findByStation(String name);

    @Query(value = "SELECT name FROM m_station", nativeQuery = true)
    List<String> findAllStations();
}
