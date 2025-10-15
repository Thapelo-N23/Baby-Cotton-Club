package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Admin;
import za.ac.cput.repository.AdminRepository;
import za.ac.cput.service.IAdminService;

import java.util.List;


@Service
public class AdminService implements IAdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Admin create(Admin admin) {
        String password = admin.getPassword();
        if (password != null && !(password.startsWith("$2a$") || password.startsWith("$2b$") || password.startsWith("$2y$"))) {
            admin.setPassword(passwordEncoder.encode(password));
        }
        return adminRepository.save(admin);
    }

    @Override
    public Admin read(Integer s) {
        return this.adminRepository.findById(s).orElse(null);
    }

    @Override
    public Admin update(Admin admin) {
        String password = admin.getPassword();
        if (password != null && !(password.startsWith("$2a$") || password.startsWith("$2b$") || password.startsWith("$2y$"))) {
            admin.setPassword(passwordEncoder.encode(password));
        }
        return adminRepository.save(admin);
    }

    @Override
    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    public Admin login(String email, String password) {
        Admin admin = adminRepository.findByEmail(email);
        if (admin != null && passwordEncoder.matches(password, admin.getPassword())) {
            return admin;
        }
        return null;
    }
}
