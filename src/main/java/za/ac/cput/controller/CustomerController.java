/**
 * BabyCottonClub
 * Product.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date : 24 May 2025
 */
package za.ac.cput.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Customer;
import za.ac.cput.service.impl.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer customer) {
        return service.create(customer);
    }

    @GetMapping("/read/{id}")
    public Customer readCustomer(@PathVariable("id") Integer customerId) {
        return service.read(customerId);
    }

    @PutMapping("/update")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return service.update(customer);
    }

    @GetMapping("/findAll")
    public List<Customer> getAllCustomers() {
        return service.getAll();
    }

    @PostMapping("/login")
    public za.ac.cput.dto.CustomerLoginResponseDto login(
        @RequestParam("email") String email,
        @RequestParam("password") String password
    ) {
        Customer customer = service.login(email);
        if (customer == null) return null;
        return new za.ac.cput.dto.CustomerLoginResponseDto(
            customer.getCustomerId(),
            customer.getEmail(),
            customer.getFirstName(),
            customer.getLastName()
        );
    }

}
