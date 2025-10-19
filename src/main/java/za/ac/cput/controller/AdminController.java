package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Admin;
import za.ac.cput.service.IAdminService;
import za.ac.cput.service.impl.AdminService;

import java.util.List;
import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    @Value("${jwt.secret}")
    private String jwtSecret;

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

        // generate JWT (24 hours expiry)
        long now = System.currentTimeMillis();
        Date issuedAt = new Date(now);
        Date expiry = new Date(now + 24 * 60 * 60 * 1000L);

        // create a proper HMAC key from the configured secret
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

        String token = Jwts.builder()
                .setSubject(admin.getEmail())
                .setIssuedAt(issuedAt)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return new za.ac.cput.dto.AdminLoginResponseDto(
            admin.getAdminId(),
            admin.getEmail(),
            token
        );
    }
}
