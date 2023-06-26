package com.example.railwayticketreservation.controllers;

import com.example.railwayticketreservation.models.Login;
import com.example.railwayticketreservation.models.Register;
import com.example.railwayticketreservation.repositories.LoginRepository;
import com.example.railwayticketreservation.repositories.RegisterRepository;
import com.example.railwayticketreservation.utilModels.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
@CrossOrigin
public class RegisterController {

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    LoginRepository loginRepository;

    @PostMapping("api/addUser")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        Optional<Register> user1 = registerRepository.findByLastName(user.getLastName());
        if (user1.isPresent()) {
            return new ResponseEntity<>("User Data Failed To Add", HttpStatus.BAD_REQUEST);
        } else {
            Register register = new Register();
            register.setEmailId(user.getEmailId());
            register.setLastName(user.getLastName());
            register.setFirstName(user.getFirstName());
            register.setDob(user.getDob());
            Register register1 = registerRepository.save(register);
            Login login = new Login();
            login.setPassword(user.getPassword());
            login.setId(register1);
            loginRepository.save(login);
            return new ResponseEntity<>("User Data Added Successfully", HttpStatus.CREATED);
        }
    }
}
