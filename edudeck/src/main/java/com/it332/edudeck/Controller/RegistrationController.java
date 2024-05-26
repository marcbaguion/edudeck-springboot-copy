package com.it332.edudeck.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.it332.edudeck.Entity.UserEntity;
import com.it332.edudeck.Service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RegistrationController {
    
    @Autowired
    private UserService userv;

    @PostMapping("/signup")
    public UserEntity insertUser(@RequestBody UserEntity user){
        return userv.insertUser(user);
    }
}