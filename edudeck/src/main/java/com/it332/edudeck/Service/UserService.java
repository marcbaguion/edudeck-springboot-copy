package com.it332.edudeck.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.Entity.UserEntity;
import com.it332.edudeck.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
    UserRepository urepo;
	
	// C
    public UserEntity insertUser(UserEntity user){
        return urepo.save(user);
    }
	
	//R -Read
    public List<UserEntity> getAllUser(){
        return urepo.findAll();
    }
    
 // Update
 	@SuppressWarnings("finally")
 	public UserEntity updateUser(int userid, UserEntity newUserDetails) {
 		UserEntity user = new UserEntity();
 		try {
 			//1.) search the id number of the user that will be updated
 			user = urepo.findById(userid).get();
 			
 			//2.) update the record
 			user.setUsername(newUserDetails.getUsername());
 			user.setEmail(newUserDetails.getEmail());
 		}catch(NoSuchElementException ex){
 			throw new NoSuchElementException("User " + userid + " does not exist!");
 		}finally {
 			return urepo.save(user);
 		}
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
