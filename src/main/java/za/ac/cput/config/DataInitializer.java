package za.ac.cput.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import za.ac.cput.repository.AdminRepository;
import za.ac.cput.service.impl.AdminService;
import za.ac.cput.domain.Admin;

@Component
public class DataInitializer {

    private final AdminService adminService;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(AdminService adminService, AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminService = adminService;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostConstruct
    public void init() {
        final String ADMIN_EMAIL = "Junior@admin.com";
        final String ADMIN_PASSWORD = "password";

        Admin existingAdmin = adminRepository.findByEmail(ADMIN_EMAIL);

        if (existingAdmin == null) {
            Admin newAdmin = new Admin.Builder()
                    .email(ADMIN_EMAIL)
                    .password(passwordEncoder.encode(ADMIN_PASSWORD))
                    .build();
            adminService.create(newAdmin);
            System.out.println("Default admin user created with email: " + ADMIN_EMAIL);
        } else {
            // Only update password if not already a BCrypt hash
            String currentPassword = existingAdmin.getPassword();
            if (currentPassword == null || !(currentPassword.startsWith("$2a$") || currentPassword.startsWith("$2b$") || currentPassword.startsWith("$2y$"))) {
                existingAdmin.setPassword(passwordEncoder.encode(ADMIN_PASSWORD));
                adminService.update(existingAdmin);
                System.out.println("Admin user already exists, password updated to bcrypt hash for email: " + ADMIN_EMAIL);
            } else {
                System.out.println("Admin user already exists, password is already a bcrypt hash for email: " + ADMIN_EMAIL);
            }
        }
    }
}
