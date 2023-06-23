package com.example.railwayticketreservation.repositories;

import com.example.railwayticketreservation.models.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Integer> {

}
