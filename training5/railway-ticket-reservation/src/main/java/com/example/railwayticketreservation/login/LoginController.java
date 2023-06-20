package com.example.railwayticketreservation.Login;

import com.example.railwayticketreservation.Register.RegisterModel;
import com.example.railwayticketreservation.Register.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LoginController {

    @Autowired
    RegisterRepository registerRepository;

    @PostMapping("/api/checkUser")
    public ResponseEntity<Optional<RegisterModel>> checkUser(@RequestBody LoginModel loginModel) {
        String userName = loginModel.getUserName();
        Optional<RegisterModel> user = registerRepository.findByUserName(userName);
        if (user.isPresent() && user.get().getUserName().compareTo(loginModel.getUserName()) == 0 &&
                user.get().getPassword().compareTo(loginModel.getPassword()) == 0) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
