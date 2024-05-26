package com.it332.edudeck.Controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it332.edudeck.Entity.UserEntity;
import com.it332.edudeck.Service.UserService;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
    
    @Autowired
    private UserService userv;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserEntity request) {
        String username = request.getUsername();
        String password = request.getPassword();

        // Your authentication logic here
        boolean isAuthenticated = userv.authenticateUser(username, password);

        if (isAuthenticated) {
            UserEntity userDetails = userv.getUserDetails(username);
            return ResponseEntity.ok(userDetails);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
