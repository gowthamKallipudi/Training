package com.example.railwayticketreservation.Register;

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

    @PostMapping("api/addUser")
    public ResponseEntity<String> saveUser(@RequestBody RegisterModel registerModel) {
        Optional<RegisterModel> user = registerRepository.findByUserName(registerModel.getUserName());
        if (user.isPresent()) {
            return new ResponseEntity<>("User Data Failed To Add", HttpStatus.BAD_REQUEST);
        } else {
            registerRepository.save(registerModel);
            return new ResponseEntity<>("User Data Added Successfully", HttpStatus.CREATED);
        }
    }

    @GetMapping("api/getUser/{name}")
    public ResponseEntity<RegisterModel> getUser(@PathVariable("name") String userName) {
        Optional<RegisterModel> user = registerRepository.findByUserName(userName);
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
    }

    @PutMapping("api/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody RegisterModel registerModel) {
        registerRepository.save(registerModel);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
