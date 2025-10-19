/**
 * BabyCottonClub
 * Product.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date : 24 May 2025
 */


package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Customer;
import za.ac.cput.repository.CustomerRepository;
import za.ac.cput.service.ICustomerService;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Customer create(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return this.customerRepository.save(customer);
    }

    @Override
    public Customer read(Integer s) {
        return this.customerRepository.findById(s).orElse(null);
    }

    @Override
    public Customer update(Customer customer) {
        // Fetch existing customer from DB
        Customer existing = customerRepository.findById(customer.getCustomerId())
                .orElseThrow();

        Customer updated = new Customer.Builder()
                .copy(existing)
                .setFirstName(customer.getFirstName())
                .setLastName(customer.getLastName())
                .setEmail(customer.getEmail())
                .setPhoneNumber(customer.getPhoneNumber())
                .password(customer.getPassword() != null && !customer.getPassword().isEmpty()
                        ? passwordEncoder.encode(customer.getPassword())
                        : existing.getPassword())
                .build();

        return customerRepository.save(updated);
    }


        @Override
    public List<Customer> getAll() {
        return this.customerRepository.findAll();
    }

    public Customer login(String email, String password) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null && passwordEncoder.matches(password, customer.getPassword())) {
            return customer;
        }
        return null;
    }
}
