package com.example.railwayticketreservation.repositories;

import com.example.railwayticketreservation.models.Register;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterRepository extends JpaRepository<Register, Integer> {
    Optional<Register> findByLastName(String lastName);

    Optional<Register> findByUserName(String userName);
}
