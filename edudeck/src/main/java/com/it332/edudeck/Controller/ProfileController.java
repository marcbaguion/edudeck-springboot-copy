package com.it332.edudeck.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.it332.edudeck.Entity.ProfileEntity;
import com.it332.edudeck.Service.ProfileService;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/create")
    public ResponseEntity<ProfileEntity> createProfile(@RequestBody ProfileEntity profileEntity) {
        ProfileEntity createdProfile = profileService.createProfile(profileEntity);
        return ResponseEntity.ok(createdProfile);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProfileEntity>> getAllProfiles() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileEntity> getProfileById(@PathVariable int id) {
        ProfileEntity profileEntity = profileService.getProfileById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found with id: " + id));
        return ResponseEntity.ok(profileEntity);
    }

    @PutMapping("/softdelete/{id}")
    public ResponseEntity<Void> softDeleteProfile(@PathVariable int id) {
        boolean isDeleted = profileService.softDeleteProfile(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
