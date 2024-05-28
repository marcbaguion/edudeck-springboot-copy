package com.it332.edudeck.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
import com.it332.edudeck.Repository.UserRepository;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
    UserService userv;

    @Autowired
    UserRepository urepo;
	
	//R -Read
    @GetMapping("/getAllUsers")
    public List<UserEntity> getAllUser(){
        return userv.getAllUser();
    }
    
	// U - Update a user record
    @PutMapping("/updateUser/{userid}")
    public UserEntity updateUser(@PathVariable int userid, @RequestBody UserEntity newUserDetails){
        return userv.updateUser(userid, newUserDetails);
    }
	

 	
 	// D - Delete a user record
 	@PutMapping("/deleteUser/{userid}")
    public ResponseEntity<java.util.Map<String, String>> deleteUser(@PathVariable int userid){
        java.util.Map<String, String> response = new HashMap<>();
        Optional<UserEntity> userOptional = urepo.findById(userid);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            user.setDeleted(true); // Set isDeleted to true instead of deleting the record
            urepo.save(user); // Save the updated user record
            response.put("message", "User " + userid + " is successfully deleted");
        }
        else{
            response.put("message", "User " + userid + " does not exist");
        }
        return ResponseEntity.ok(response);
    }
}
