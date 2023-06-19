package com.example.railwayticketreservation;

import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/")
public class Controller {

    @PostMapping("/login")
    public void checkCredentials(@RequestBody Model model) throws SQLException {
        boolean state = DB.checkCredentials(model);
        System.out.println(state);
    }
}
