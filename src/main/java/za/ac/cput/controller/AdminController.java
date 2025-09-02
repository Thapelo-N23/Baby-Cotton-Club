package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Admin;
import za.ac.cput.service.IAdminService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final IAdminService IAdminService;

    @Autowired
    public AdminController(IAdminService IAdminService) {
        this.IAdminService = IAdminService;
    }

    @PostMapping("/create")
    public Admin createAdmin(@RequestBody Admin admin) {
        return IAdminService.create(admin);
    }

    @GetMapping("/read/{id}")
    public Admin readAdmin(@PathVariable("id") Integer adminId) {
        return IAdminService.read(adminId);
    }

    @PutMapping("/update")
    public Admin updateAdmin(@RequestBody Admin admin) {
        return IAdminService.update(admin);
    }

    @GetMapping("/findAll")
    public List<Admin> getAllAdmins() {
        return IAdminService.getAll();
    }
}
