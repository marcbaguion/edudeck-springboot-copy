package com.it332.edudeck.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.it332.edudeck.Repository.UserRepository;
import com.it332.edudeck.Service.UserService;
import com.it332.edudeck.Entity.UserEntity;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
    UserService userv;

    @Autowired
    UserRepository urepo;
    
    //C - Create and Insert
    @PostMapping("/insertUser")
    public UserEntity insertUser(@RequestBody UserEntity user){
        return userv.insertUser(user);
    }
	
	//R -Read
    @GetMapping("/getAllUsers")
    public List<UserEntity> getAllUser(){
        return userv.getAllUser();
    }
    
    // U - Update a user record
 	@PutMapping("/updateUser")
 	public UserEntity updateUser(@RequestParam int userid,@RequestBody UserEntity newUserDetails) {
 		return userv.updateUser(userid, newUserDetails);
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
