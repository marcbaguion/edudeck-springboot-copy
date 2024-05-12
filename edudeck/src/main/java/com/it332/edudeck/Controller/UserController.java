package com.it332.edudeck.Controller;

import java.util.List;
import java.util.NoSuchElementException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.it332.edudeck.Service.UserService;
import com.it332.edudeck.Entity.UserEntity;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
    UserService userv;
	
	//R -Read
    @GetMapping("/getAllUsers")
    public List<UserEntity> getAllUser(){
        return userv.getAllUser();
    }
    
	// U - Update a user record
    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestParam int userid, @RequestBody UserEntity newUserDetails) {
        try {
            UserEntity updatedUser = userv.updateUser(userid, newUserDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }
	

 	
 	// D - Delete a user record
 	@DeleteMapping("/deleteUser/{userid}")
 	public String deleteUser(@PathVariable int userid) {
 		return userv.deleteUser(userid);
 	}

	
	@GetMapping("/helloworld")
	public String printHelloWorld(){
		return "Hello World!";
	}
}
