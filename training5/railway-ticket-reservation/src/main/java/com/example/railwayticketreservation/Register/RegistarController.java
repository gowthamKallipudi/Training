//package com.example.railwayticketreservation.Register;
//
//import com.example.railwayticketreservation.models.Login;
//import com.example.railwayticketreservation.models.Register;
//import com.example.railwayticketreservation.repositories.LoginRepository;
//import com.example.railwayticketreservation.repositories.RegistarRepository;
//import com.example.railwayticketreservation.utilModels.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/")
//@CrossOrigin
//public class RegisterController {
//
//    @Autowired
//    RegisterRepository registerRepository;
//
//    @Autowired
//    RegistarRepository regRepository;
//
//    @Autowired
//    LoginRepository loginRepository;
//
//    @Autowired
//    RegistarRepository registarRepository;
//
//
//    @PostMapping("api/addUser")
//    public ResponseEntity<String> saveUser(@RequestBody RegisterModel registerModel) {
//        Optional<RegisterModel> user = registerRepository.findByUserName(registerModel.getUserName());
//        if (user.isPresent()) {
//            return new ResponseEntity<>("User Data Failed To Add", HttpStatus.BAD_REQUEST);
//        } else {
//            registerRepository.save(registerModel);
//            return new ResponseEntity<>("User Data Added Successfully", HttpStatus.CREATED);
//        }
//    }
//
//    @GetMapping("api/getUser/{name}")
//    public ResponseEntity<RegisterModel> getUser(@PathVariable("name") String userName) {
//        Optional<RegisterModel> user = registerRepository.findByUserName(userName);
//        if (user.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        } else {
//            return new ResponseEntity<>(user.get(), HttpStatus.OK);
//        }
//    }
//
//    @PutMapping("api/updateUser")
//    public ResponseEntity<String> updateUser(@RequestBody RegisterModel registerModel) {
//        registerRepository.save(registerModel);
//        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
//    }
//
//    @PostMapping("api/adUser")
//    public ResponseEntity<String> savUser(@RequestBody User user) {
//        Optional<Register> user1 = regRepository.findByLastName(user.getLastName());
//        if (user1.isPresent()) {
//            return new ResponseEntity<>("User Data Failed To Add", HttpStatus.BAD_REQUEST);
//        } else {
//            Register register = new Register();
//            register.setEmailId(user.getEmailId());
//            register.setLastName(user.getLastName());
//            register.setFirstName(user.getFirstName());
//            register.setDob(user.getDob());
//            Register register1 = regRepository.save(register);
//            Login login = new Login();
//            login.setPassword(user.getPassword());
//            login.setId(register1);
//            login = loginRepository.save(login);
//            registarRepository.save(register);
//            return new ResponseEntity<>("User Data Added Successfully", HttpStatus.CREATED);
//        }
//    }
//}
