package za.ac.cput.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.service.ICustomerService;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerServiceTest {


@Autowired
private ICustomerService service;
    private static Customer customer = CustomerFactory.createCustomer(
            "John",
            "Doe",
            "mengezi@gmail.com",
            "0781234567",
            Collections.emptyList(),
            Collections.emptyList(),
            Collections.emptyList()
    );
    @Test
    void create() {
        Customer created = service.create(customer);
        assertNotNull(created);
        System.out.println("Created: " + created);
    }
    @Test
    void read() {
        Customer read = service.read(customer.getCustomerId());
        assertNotNull(read);
        System.out.println("Read: " + read);
    }
    @Test
    void update() {
        Customer updated = new Customer.Builder()
                .copy(customer)
                .setFirstName("Jane")
                .setLastName("Smith")
                .build();
    }
    @Test
    void getAll() {
        assertNotNull(service.getAll());
        System.out.println("All Customers: " + service.getAll());
        assertFalse(service.getAll().isEmpty(), "Customer list should not be empty");
    }
}