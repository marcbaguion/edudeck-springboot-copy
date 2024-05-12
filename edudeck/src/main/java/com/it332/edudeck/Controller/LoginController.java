package com.it332.edudeck.Controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.it332.edudeck.Entity.UserEntity;
import com.it332.edudeck.Repository.UserRepository;

@RestController
public class LoginController {
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserEntity user) {
        // Retrieve the user from the database based on the provided username
        UserEntity storedUser = userRepository.findByUsername(user.getUsername());

        // Check if the user exists and if the provided password matches the stored hashed password
        if (storedUser != null && BCrypt.checkpw(user.getPassword(), storedUser.getPassword())) {
            return ResponseEntity.ok("Login successful!"); // Passwords match
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password."); // Passwords don't match or user doesn't exist
        }
    }
}
