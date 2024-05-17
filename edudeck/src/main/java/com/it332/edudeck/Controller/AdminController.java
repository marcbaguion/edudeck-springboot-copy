package com.it332.edudeck.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.it332.edudeck.Entity.AdminEntity;
import com.it332.edudeck.Service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/create")
    public ResponseEntity<AdminEntity> createAdmin(@RequestBody AdminEntity admin) {
        AdminEntity createdAdmin = adminService.createAdmin(admin);
        return ResponseEntity.ok(createdAdmin);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AdminEntity>> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminEntity> getAdminById(@PathVariable int id) {
        AdminEntity admin = adminService.getAdminById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
        return ResponseEntity.ok(admin);
    }

    @PutMapping("/softdelete/{id}")
    public ResponseEntity<Void> softDeleteAdmin(@PathVariable int id) {
        boolean isDeleted = adminService.softDeleteAdmin(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
