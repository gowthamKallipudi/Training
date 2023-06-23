package com.example.railwayticketreservation.repositories;

import com.example.railwayticketreservation.models.Login;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoginRepository extends JpaRepository<Login, String> {

}
