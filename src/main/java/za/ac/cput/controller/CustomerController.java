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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    // ✅ Create
    @PostMapping("/create")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody Customer customer) {
        Customer saved = service.create(customer);
        return ResponseEntity.ok(CustomerMapper.toDto(saved));
    }

    // ✅ Read by ID
    @GetMapping("/read/{id}")
    public ResponseEntity<CustomerResponse> readCustomer(@PathVariable("id") Integer customerId) {
        Customer found = service.read(customerId);
        if (found == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(CustomerMapper.toDto(found));
    }

    // ✅ Update
    @PutMapping("/update")
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody Customer customer) {
        Customer updated = service.update(customer);
        return ResponseEntity.ok(CustomerMapper.toDto(updated));
    }

    // ✅ Get all customers (proper mapping)
    @GetMapping("/findAll")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> customers = service.getAll()
                .stream()
                .map(CustomerMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(customers);
    }

    // ✅ Login endpoint
    @PostMapping("/login")
    public ResponseEntity<CustomerLoginResponseDto> login(@RequestParam("email") String email,
                                                          @RequestParam("password") String password) {
        Customer customer = service.login(email);
        if (customer == null) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }
        return ResponseEntity.ok(
                new CustomerLoginResponseDto(
                        customer.getCustomerId(),
                        customer.getEmail(),
                        customer.getFirstName(),
                        customer.getLastName()
                )
        );
    }
}
