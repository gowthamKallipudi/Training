package com.example.railwayticketreservation.Register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin
public class RegisterController {

    @Autowired
    RegisterRepository registerRepository;

    @PostMapping("api/addUser")
    public ResponseEntity<RegisterModel> saveUser(@RequestBody RegisterModel registerModel) {
        System.out.println(registerModel);
        return new ResponseEntity<>(registerRepository.save(registerModel), HttpStatus.CREATED);
    }

}
