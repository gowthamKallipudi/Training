package com.example.railwayticketreservation.repositories;

import com.example.railwayticketreservation.models.TrainCoach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TrainCoachRepository extends JpaRepository<TrainCoach, String> {

    @Query(value = "SELECT * from m_train_coach_info where coach = ?1", nativeQuery = true)
    TrainCoach findByCoach(String coach);
}
