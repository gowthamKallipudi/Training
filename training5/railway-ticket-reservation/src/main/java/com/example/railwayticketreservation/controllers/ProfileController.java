package com.example.railwayticketreservation.controllers;

import com.example.railwayticketreservation.models.Register;
import com.example.railwayticketreservation.repositories.RegisterRepository;
import com.example.railwayticketreservation.utilModels.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
@CrossOrigin
public class ProfileController {

    @Autowired
    RegisterRepository registerRepository;

    @PutMapping("/api/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody Profile profile) {
        Optional<Register> register = registerRepository.findById(profile.getId());
        Optional<Register> newRegister = registerRepository.findByLastName(profile.getLastName());
        if(newRegister.isEmpty() && register.isPresent()) {
            register.get().setLastName(profile.getLastName());
            register.get().setFirstName(profile.getFirstName());
            register.get().setEmailId(profile.getEmailId());
            register.get().setDob(profile.getDob());
            register.get().setLatestPage(profile.getLatestPage());
            registerRepository.save(register.get());
            return new ResponseEntity<>("User Updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User Not Updated", HttpStatus.BAD_REQUEST);
        }
    }
}
