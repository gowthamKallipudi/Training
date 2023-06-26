package com.example.railwayticketreservation.repositories;

import com.example.railwayticketreservation.models.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TrainRepository extends JpaRepository<Train, Integer> {

    @Query(value = "SELECT id from m_train where name = ?1", nativeQuery = true)
    Integer findIdByName(String name);

    @Query(value = "SELECT name from m_train where id = ?1", nativeQuery = true)
    String findNameById(Integer id);
}
