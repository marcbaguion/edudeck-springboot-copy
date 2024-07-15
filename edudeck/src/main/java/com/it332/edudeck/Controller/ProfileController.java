package com.it332.edudeck.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.it332.edudeck.Entity.Profile;
import com.it332.edudeck.Entity.User;
import com.it332.edudeck.Repository.ProfileRepository;
import com.it332.edudeck.Repository.UserRepository;
import com.it332.edudeck.Service.ProfileService;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserRepository urepo;

    @Autowired
    private ProfileRepository prepo;

    @PostMapping("/uploadProfilePicture")
    public Profile uploadProfilePicture(@RequestParam("userno") int userno, @RequestParam("file") MultipartFile file) {
    byte[] profilePicture = null;
    try {
        profilePicture = file.getBytes();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return profileService.saveProfilePicture(userno, profilePicture);
    }

    @GetMapping("/getProfilePicture/{userno}")
    public byte[] getProfilePicture(@PathVariable int userno) {
        User user = urepo.findById(userno).orElse(null);
        Profile profile = prepo.findByUser(user);
        return profile != null ? profile.getProfilePicture() : null;
    }
}
