package za.ac.cput.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.cput.repository.AdminRepository;
import za.ac.cput.service.impl.AdminService;
import za.ac.cput.domain.Admin;

@Component
public class DataInitializer {

    private final AdminService adminService;
    private final AdminRepository adminRepository;

    @Autowired
    public DataInitializer(AdminService adminService, AdminRepository adminRepository) {
        this.adminService = adminService;
        this.adminRepository = adminRepository;
    }


    @PostConstruct
    public void init() {
        final String ADMIN_EMAIL = "Junior@admin.com";
        final String ADMIN_PASSWORD = "password";

        Admin existingAdmin = adminRepository.findByEmail(ADMIN_EMAIL);

        if (existingAdmin == null) {
            Admin newAdmin = new Admin.Builder()
                    .email(ADMIN_EMAIL)
                    .password(ADMIN_PASSWORD)
                    .build();

            adminService.create(newAdmin);
            System.out.println("Default admin user created with email: " + ADMIN_EMAIL);
        } else {
            System.out.println("Admin user already exists with email: " + ADMIN_EMAIL);
        }
    }
}
