package com.example.railwayticketreservation.repositories;

import com.example.railwayticketreservation.models.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, String> {

}
