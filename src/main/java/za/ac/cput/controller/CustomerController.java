/**
 * BabyCottonClub
 * CustomerController.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date : 24 May 2025
 */

package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Customer;
import za.ac.cput.dto.CustomerLoginResponseDto;
import za.ac.cput.dto.CustomerResponse;
import za.ac.cput.mapper.CustomerMapper;
import za.ac.cput.service.impl.CustomerService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import za.ac.cput.config.JwtKeyProvider;

import java.security.Key;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService service;

    @Autowired
    private JwtKeyProvider jwtKeyProvider;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody Customer customer) {
        Customer saved = service.create(customer);
        return ResponseEntity.ok(CustomerMapper.toDto(saved));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<CustomerResponse> readCustomer(@PathVariable("id") Integer customerId) {
        Customer found = service.read(customerId);
        if (found == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(CustomerMapper.toDto(found));
    }


    @PutMapping("/update")
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody Customer customer) {
        Customer updated = service.update(customer);
        return ResponseEntity.ok(CustomerMapper.toDto(updated));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> customers = service.getAll()
                .stream()
                .map(CustomerMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(customers);
    }

    @PostMapping("/login")
    public ResponseEntity<CustomerLoginResponseDto> login(@RequestParam("email") String email,
                                                          @RequestParam("password") String password) {
        Customer customer = service.login(email, password);
        if (customer == null) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }

        // build a JWT for the customer (24 hours)
        long now = System.currentTimeMillis();
        Date issuedAt = new Date(now);
        Date expiry = new Date(now + 24 * 60 * 60 * 1000L);
        Key key = jwtKeyProvider.getKey();

        String token = Jwts.builder()
                .setSubject(customer.getEmail())
                .claim("roles", java.util.List.of("ROLE_CUSTOMER"))
                .setIssuedAt(issuedAt)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return ResponseEntity.ok(
                new CustomerLoginResponseDto(
                        customer.getCustomerId(),
                        customer.getEmail(),
                        customer.getFirstName(),
                        customer.getLastName(),
                        token
                )
        );
    }
}
