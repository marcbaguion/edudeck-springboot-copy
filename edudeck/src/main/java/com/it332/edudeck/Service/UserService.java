package com.it332.edudeck.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.Entity.UserEntity;
import com.it332.edudeck.Repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

@Service
public class UserService {
	@Autowired
    UserRepository urepo;
	
	// C
    public UserEntity insertUser(UserEntity user){
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        return urepo.save(user);
    }

    //R -Read
    public List<UserEntity> getAllUser(){
        return urepo.findAll().stream().filter(user -> !user.isDeleted()).collect(Collectors.toList());
    }

    //U
    @SuppressWarnings("finally")
    public UserEntity updateUser(int userid, UserEntity newUserDetails){
        UserEntity user = new UserEntity();
        try{
            // 1. Search the id number of the user that will be updated
            user = urepo.findById(userid).get();

            // 2. Update
            user.setUsername(newUserDetails.getUsername());
            user.setPassword(newUserDetails.getPassword());
            user.setEmail(newUserDetails.getEmail());
        }
        catch(NoSuchElementException e){
            throw new NoSuchElementException("User " + userid + " does not exist!");
        }
        finally {
            return urepo.save(user);
        }
    }

    //D - Delete
    public String deleteUser(int userid){
        String msg = "";

        Optional<UserEntity> userOptional = urepo.findById(userid);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            user.setDeleted(true); // Set isDeleted to true instead of deleting the record
            urepo.save(user); // Save the updated user record
            msg = "User " + userid + " is successfully deleted";
        }
        else{
            msg = "User " + userid + " does not exist";
        }
        return msg;
    }

	public boolean authenticateUser(String username, String rawPassword) {
        UserEntity user = urepo.findByUsernameAndIsDeletedFalse(username);
        if (user != null) {
            return BCrypt.checkpw(rawPassword, user.getPassword());
        }
        return false;
    }

    public UserEntity getUserDetails(String username) {
        return urepo.findByUsernameAndIsDeletedFalse(username);
    }

    // Retrieve user by ID
    public UserEntity findUserById(int userid) {
        return urepo.findById(userid)
                    .orElseThrow(() -> new NoSuchElementException("User " + userid + " does not exist"));
    }
}
