package com.it332.edudeck.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.it332.edudeck.Entity.UserEntity;
import com.it332.edudeck.Service.UserService;

@RestController
public class RegistrationController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserEntity user){
        try {
            UserEntity insertedUser = userService.insertUser(user);
            String successMessage = "Registration successful! Welcome, " + insertedUser.getUsername() + "!";
            return ResponseEntity.ok(successMessage);
        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }
}