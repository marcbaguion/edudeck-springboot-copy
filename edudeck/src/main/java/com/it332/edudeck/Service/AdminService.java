package com.it332.edudeck.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.Entity.AdminEntity;
import com.it332.edudeck.Repository.AdminRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public AdminEntity createAdmin(AdminEntity admin) {
        return adminRepository.save(admin);
    }

    public List<AdminEntity> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Optional<AdminEntity> getAdminById(int id) {
        return adminRepository.findById(id);
    }

    public boolean softDeleteAdmin(int id) {
        Optional<AdminEntity> adminOptional = adminRepository.findById(id);
        if (adminOptional.isPresent()) {
            AdminEntity admin = adminOptional.get();
            admin.setDeleted(true);
            adminRepository.save(admin);
            return true;
        } else {
            return false;
        }
    }
}
