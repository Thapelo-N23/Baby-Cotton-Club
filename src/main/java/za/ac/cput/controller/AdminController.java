package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Admin;
import za.ac.cput.service.IAdminService;
import za.ac.cput.service.impl.AdminService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/create")
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.create(admin);
    }

    @GetMapping("/read/{id}")
    public Admin readAdmin(@PathVariable("id") Integer adminId) {
        return adminService.read(adminId);
    }

    @PutMapping("/update")
    public Admin updateAdmin(@RequestBody Admin admin) {
        return adminService.update(admin);
    }

    @GetMapping("/findAll")
    public List<Admin> getAllAdmins() {
        return adminService.getAll();
    }

    @PostMapping("/login")
    public za.ac.cput.dto.AdminLoginResponseDto login(
        @RequestParam("email") String email,
        @RequestParam("password") String password
    ) {
        Admin admin = adminService.login(email, password);
        if (admin == null) return null;
        return new za.ac.cput.dto.AdminLoginResponseDto(
            admin.getAdminId(),
            admin.getEmail()
        );
    }
}
