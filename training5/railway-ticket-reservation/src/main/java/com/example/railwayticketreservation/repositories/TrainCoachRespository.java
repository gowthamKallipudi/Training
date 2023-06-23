package com.example.railwayticketreservation.repositories;

import com.example.railwayticketreservation.models.TrainCoach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainCoachRespository extends JpaRepository<TrainCoach, String> {
}
