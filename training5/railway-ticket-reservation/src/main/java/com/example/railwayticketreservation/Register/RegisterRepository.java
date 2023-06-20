package com.example.railwayticketreservation.Register;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterRepository extends JpaRepository<RegisterModel, Long> {
    Optional<RegisterModel> findByUserName(String emailId);
}
