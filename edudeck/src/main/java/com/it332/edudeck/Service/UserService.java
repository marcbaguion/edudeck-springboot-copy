package com.it332.edudeck.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.it332.edudeck.Entity.UserEntity;
import com.it332.edudeck.Repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

@Service
public class UserService {
	@Autowired
    UserRepository urepo;
	
	// C
    public UserEntity insertUser(UserEntity user){
		// Check if a user with the provided email or username already exists
		if (urepo.existsByUsername(user.getUsername()) || urepo.existsByEmail(user.getEmail())) {
			throw new IllegalArgumentException("User with email or username already exists.");
		}
		// Hash the password
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        // Set the hashed password back to the user object
        user.setPassword(hashedPassword);
        return urepo.save(user);
    }
	
	//R -Read
    public List<UserEntity> getAllUser(){
        return urepo.findAll();
    }
    
	public UserEntity updateUser(int userid, UserEntity newUserDetails) {
		// Retrieve the user from the database
		UserEntity user = urepo.findById(userid).orElseThrow(() -> new NoSuchElementException("User " + userid + " does not exist!"));
	
		// Check if the new username is unique
		if (!user.getUsername().equals(newUserDetails.getUsername()) && urepo.existsByUsername(newUserDetails.getUsername())) {
			throw new IllegalArgumentException("Username is already in use.");
		}
	
		// Check if the new email is unique
		if (!user.getEmail().equals(newUserDetails.getEmail()) && urepo.existsByEmail(newUserDetails.getEmail())) {
			throw new IllegalArgumentException("Email is already in use.");
		}
	
		// Update the user details
		user.setUsername(newUserDetails.getUsername());
		user.setEmail(newUserDetails.getEmail());
		user.setName(newUserDetails.getName());
		user.setBio(newUserDetails.getBio());

		// If newPassword is provided, update the password
        if (newUserDetails.getPassword() != null && !newUserDetails.getPassword().isEmpty()) {
            // Hash the new password
            String hashedPassword = BCrypt.hashpw(newUserDetails.getPassword(), BCrypt.gensalt());
            // Set the hashed password back to the user object
            user.setPassword(hashedPassword);
        }

		// If a new profile picture file is provided, update the profile picture
		// if (profilePictureFile != null && !profilePictureFile.isEmpty()) {
		// 	try {
		// 		// Save the uploaded profile picture to the user entity
		// 		user.setProfilePicture(profilePictureFile.getBytes());
		// 	} catch (IOException e) {
		// 		throw new IllegalStateException("Failed to update profile picture.");
		// 	}
		// }
	
		// Save the updated user to the database
		return urepo.save(user);
	}

	// Update Password
    public UserEntity updatePassword(int userId, String newPassword) {
        // Retrieve the user from the database
        UserEntity user = urepo.findById(userId)
                                        .orElseThrow(() -> new NoSuchElementException("User " + userId + " does not exist!"));
        
        // Hash the new password
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        
        // Set the hashed password back to the user object
        user.setPassword(hashedPassword);
        
        // Save the updated user with the new password
        return urepo.save(user);
    }
	
	// Update Profile Picture
    public UserEntity updateProfilePicture(int userId, MultipartFile file) throws IOException {
        // Retrieve the user from the database
        UserEntity user = urepo.findById(userId)
                                        .orElseThrow(() -> new NoSuchElementException("User " + userId + " does not exist!"));

        // Save the uploaded profile picture to the user entity
        user.setProfilePicture(file.getBytes());
        
        // Save the updated user with the new profile picture to the database
        return urepo.save(user);
    }

    //Delete
 	public String deleteUser(int userid) {
		String msg = "";
		
		if(urepo.findById(userid) != null) {
			urepo.deleteById(userid);
			msg = "User " + userid + " is successfully deleted!";
		}else
			msg = "User " + userid + " does not exist.";
		return msg;
	}
}
