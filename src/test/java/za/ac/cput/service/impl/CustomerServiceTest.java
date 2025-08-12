
package za.ac.cput.service.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.CustomerOrder;

import za.ac.cput.domain.Review;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.factory.CustomerOrderFactory;
import za.ac.cput.factory.ReviewFactory;
import za.ac.cput.service.ICustomerService;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerServiceTest {

    @Autowired
    private ICustomerService customerService;

    private static Customer customer;
    private static Address address;
    private static CustomerOrder order;
    private static Review review;

    @Test
    void create() {
        // Create base customer
        customer = CustomerFactory.createCustomer(
                "John",
                "Doe",
                "mengezi@gmail.com",
                "0781234567",
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );

        Customer created = customerService.create(customer);
        assertNotNull(created);
        customer = created; // Save for later tests
        System.out.println("Created Customer: " + customer);
    }

    @Test
    void read() {
        Customer readCustomer = customerService.read(customer.getCustomerId());
        assertNotNull(readCustomer);
        System.out.println("Read Customer: " + readCustomer);
    }

    @Test
    void update() {
        Customer updated = new Customer.Builder()
                .copy(customer)
                .setPhoneNumber("0712345678")
                .build();

        Customer result = customerService.update(updated);
        assertNotNull(result);
        customer = result;
        System.out.println("Updated Customer: " + customer);
    }

    @Test
    void createCustomerWithRelationships() {
        // Create relationships
        address = AddressFactory.createAddress(
                "Bush St", (short) 123, "Soweto", "Johannesburg", (short) 1634, "Gauteng", customer
        );

        order = CustomerOrderFactory.createCustomerOrder(
                "20250518", 250.00, new java.util.ArrayList<>(), customer
        );

        review = ReviewFactory.createReview(
                (short) 4, "Great service!", "20250503", customer, null
        );

        Customer customerWithRelations = CustomerFactory.createCustomer(
                "John",
                "Doe",
                "mengezi@gmail.com",
                "0781234567",
                Arrays.asList(address),
                Arrays.asList(order),
                Arrays.asList(review)
        );

        Customer saved = customerService.create(customerWithRelations);
        assertNotNull(saved);
        System.out.println("Customer With Relationships: " + saved);
    }

    @Test
    void getAll() {
        assertNotNull(customerService.getAll());
        System.out.println("All Customers: " + customerService.getAll());
    }


}
