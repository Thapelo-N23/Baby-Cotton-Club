package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Admin;
import za.ac.cput.service.impl.AdminService;

import java.util.List;
import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;

import za.ac.cput.config.JwtKeyProvider;
import za.ac.cput.dto.AdminLoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    private JwtKeyProvider jwtKeyProvider;

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
    public ResponseEntity<za.ac.cput.dto.AdminLoginResponseDto> login(
        @RequestBody(required = false) AdminLoginRequest body,
        @RequestParam(value = "email", required = false) String email,
        @RequestParam(value = "password", required = false) String password
    ) {
        // allow JSON body or form/query params for compatibility
        if (body != null && body.getEmail() != null) {
            email = body.getEmail();
            password = body.getPassword();
        }

        if (email == null || password == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Admin admin = adminService.login(email, password);
        if (admin == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        // generate JWT (24 hours expiry)
        long now = System.currentTimeMillis();
        Date issuedAt = new Date(now);
        Date expiry = new Date(now + 24 * 60 * 60 * 1000L);

        Key key = jwtKeyProvider.getKey();

        String token = Jwts.builder()
                .setSubject(admin.getEmail())
                .claim("roles", java.util.List.of("ROLE_ADMIN"))
                .setIssuedAt(issuedAt)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        za.ac.cput.dto.AdminLoginResponseDto resp = new za.ac.cput.dto.AdminLoginResponseDto(
            admin.getAdminId(),
            admin.getEmail(),
            token
        );

        return ResponseEntity.ok(resp);
    }
}
