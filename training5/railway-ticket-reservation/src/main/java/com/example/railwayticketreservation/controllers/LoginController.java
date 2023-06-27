package com.example.railwayticketreservation.controllers;

import com.example.railwayticketreservation.models.Register;
import com.example.railwayticketreservation.repositories.RegisterRepository;
import com.example.railwayticketreservation.utilModels.LoginCheck;
import com.example.railwayticketreservation.utilModels.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
@CrossOrigin
public class LoginController {

    @Autowired
    RegisterRepository registerRepository;

    @PostMapping("api/checkUser")
    public ResponseEntity<?> checkUser(@RequestBody LoginCheck loginCheck){
        Optional<Register> user = registerRepository.findByLastName(loginCheck.getUserName());
        if(user.isPresent()) {
            System.out.println(user.get());
            System.out.println(user.get().getIdLogin().getPassword());
            if((user.get().getIdLogin().getPassword()).compareTo(loginCheck.getPassword()) == 0) {
                Profile profile = new Profile();
                profile.setId(user.get().getId());
                profile.setEmailId(user.get().getEmailId());
                profile.setLastName(user.get().getLastName());
                profile.setFirstName(user.get().getFirstName());
                profile.setDob(user.get().getDob());
                profile.setLatestPage(user.get().getLatestPage());
                return new ResponseEntity<>(profile, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User Not Authenticated", HttpStatus.UNAUTHORIZED);
            }
        }else {
            System.out.println("not found");
            return new ResponseEntity<>("Bad Credentials", HttpStatus.BAD_REQUEST);
        }
    }
}
