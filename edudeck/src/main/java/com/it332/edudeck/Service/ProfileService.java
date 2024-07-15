package com.it332.edudeck.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.Entity.Profile;
import com.it332.edudeck.Entity.User;
import com.it332.edudeck.Repository.ProfileRepository;
import com.it332.edudeck.Repository.UserRepository;


@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    public Profile saveProfilePicture(int userno, byte[] profilePicture) {
        User user = userRepository.findById(userno).orElse(null);
        Profile profile = profileRepository.findByUser(user);
        if (profile == null) {
            profile = new Profile();
            profile.setUser(user);
        }
        profile.setProfilePicture(profilePicture);
        return profileRepository.save(profile);
    }
}
