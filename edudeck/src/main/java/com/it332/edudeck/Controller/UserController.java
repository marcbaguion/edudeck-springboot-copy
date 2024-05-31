package com.it332.edudeck.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PutMapping("/updateBio/{userid}")
    public ResponseEntity<String> updateBio(@PathVariable int userid, @RequestBody Map<String, String> requestBody) {
        String newBio = requestBody.get("bio");
        userv.updateBio(userid, newBio);
        return ResponseEntity.ok("Bio updated successfully");
    }

    @PutMapping("/updateName/{userid}")
    public ResponseEntity<String> updateName(@PathVariable int userid, @RequestBody Map<String, String> requestBody) {
        String newName = requestBody.get("username");
        userv.updateName(userid, newName);
        return ResponseEntity.ok("Name updated successfully");
    }

    @PutMapping("/updateMobileNumber/{userid}")
    public ResponseEntity<String> updateMobileNumber(@PathVariable int userid, @RequestBody Map<String, String> requestBody) {
        String newMobileNumber = requestBody.get("mobileNumber");
        userv.updateMobileNumber(userid, newMobileNumber);
        return ResponseEntity.ok("Mobile number updated successfully");
    }

    @PutMapping("/changePassword/{userid}")
    public ResponseEntity<String> changePassword(@PathVariable int userid, @RequestBody Map<String, String> requestBody) {
        String newPassword = requestBody.get("password");
        userv.changePassword(userid, newPassword);
        return ResponseEntity.ok("Password updated successfully");
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

@PostMapping("/uploadProfilePicture/{userid}")
public ResponseEntity<Map<String, String>> uploadProfilePicture(@PathVariable int userid, @RequestParam("file") MultipartFile file) {
    Map<String, String> response = new HashMap<>();
    try {
        byte[] pictureBytes = file.getBytes();
        userv.updateProfilePicture(userid, pictureBytes);
        response.put("message", "Profile picture uploaded successfully");
        return ResponseEntity.ok(response);
    } catch (NoSuchElementException e) {
        response.put("message", "User not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    } catch (Exception e) {
        response.put("message", "Error uploading profile picture");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

    @GetMapping("/getProfilePicture/{userid}")
    public ResponseEntity<byte[]> getProfilePicture(@PathVariable int userid) {
        UserEntity user = userv.findUserById(userid);
        byte[] profilePicture = user.getProfilePicture();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(profilePicture);
    }

    @DeleteMapping("/deleteProfilePicture/{userid}")
    public ResponseEntity<Map<String, String>> deleteProfilePicture(@PathVariable int userid) {
        Map<String, String> response = new HashMap<>();
        try {
            userv.deleteProfilePicture(userid);
            response.put("message", "Profile picture deleted successfully");
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            response.put("message", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response.put("message", "Error deleting profile picture");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/getUserDetails/{userid}")
    public ResponseEntity<UserEntity> getUserDetails(@PathVariable int userid) {
        try {
            UserEntity user = userv.findUserById(userid);
            return ResponseEntity.ok(user);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/updateUserDetails/{userid}")
    public ResponseEntity<String> updateUserDetails(@PathVariable int userid, @RequestBody UserEntity updatedUser) {
        try {
            userv.updateUser(userid, updatedUser);
            return ResponseEntity.ok("User details updated successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user details");
        }
    }
}






