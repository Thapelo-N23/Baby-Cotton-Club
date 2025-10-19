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
        // Hash password before saving
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    @Override
    public Customer read(Integer customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    @Override
    public Customer update(Customer customer) {
        // Fetch existing customer from DB
        Customer existing = customerRepository.findById(customer.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Build updated customer object using Builder pattern
        Customer updated = new Customer.Builder()
                .copy(existing)
                .setFirstName(customer.getFirstName())
                .setLastName(customer.getLastName())
                .setEmail(customer.getEmail())
                .setPhoneNumber(customer.getPhoneNumber())
                // Encode and update password only if provided
                .setPassword((customer.getPassword() != null && !customer.getPassword().isEmpty())
                        ? passwordEncoder.encode(customer.getPassword())
                        : existing.getPassword())
                .build();

        return customerRepository.save(updated);
    }


    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer login(String email, String password) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null && passwordEncoder.matches(password, customer.getPassword())) {
            return customer;
        }
        return null;
    }
}
