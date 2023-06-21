package com.example.railwayticketreservation.Login;

import com.example.railwayticketreservation.Register.RegisterModel;
import com.example.railwayticketreservation.Register.RegisterRepository;
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
    public ResponseEntity<?> checkUser(@RequestBody LoginModel loginModel) {
        Optional<RegisterModel> user = registerRepository.findByUserName(loginModel.getUserName());
        if (user.isPresent() && user.get().getUserName().compareTo(loginModel.getUserName()) == 0 &&
                user.get().getPassword().compareTo(loginModel.getPassword()) == 0) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User Not Authenticated", HttpStatus.UNAUTHORIZED);
        }
    }
}
