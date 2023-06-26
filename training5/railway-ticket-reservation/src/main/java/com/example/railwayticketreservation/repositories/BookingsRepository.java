package com.example.railwayticketreservation.repositories;

import com.example.railwayticketreservation.models.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookingsRepository extends JpaRepository<Bookings, Integer> {

    @Query(value = "SELECT * FROM t_booking WHERE user_id = ?1", nativeQuery = true)
    List<Bookings> findAllByUserId(Integer user);

    @Query(value = "SELECT COUNT(*) FROM t_booking WHERE coach = ?1 AND route_id = ?2 AND date = ?3", nativeQuery = true)
    Integer findByCoach(String coach, Integer routeId, LocalDate date);
}
