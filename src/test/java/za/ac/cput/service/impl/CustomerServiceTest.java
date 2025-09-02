/**
 * BabyCottonClub
 * Product.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date :  2025
 */

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

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class CustomerServiceTest {

    @Autowired
    private ICustomerService customerService;

    private static Customer customer;
    private static Address address;
    private static CustomerOrder customerOrder;
    private static Review review;

    @Test
    void a_create() {
        customer = CustomerFactory.createCustomer(
                "John",
                "Doe",
                "mengezi@gmail.com",
                "0781234567",
                "securePassword123",
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                null
        );

        Customer created = customerService.create(customer);
        assertNotNull(created);
        customer = created;
        System.out.println("Created Customer: " + customer);
    }

    @Test
    void b_read() {
        Customer readCustomer = customerService.read(customer.getCustomerId());
        assertNotNull(readCustomer);
        System.out.println("Read Customer: " + readCustomer);
    }

    @Test
    void c_update() {
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
    void d_createCustomerWithRelationships() {
        address = AddressFactory.createAddress(
                "Bush St", (short) 123, "Soweto", "Johannesburg", (short) 1634, "Gauteng", customer
        );

        customerOrder = CustomerOrderFactory.createCustomerOrder(
                "20250627",
                150.00,
                null,
                customer,
                null



        );

        review = ReviewFactory.createReview(
                (short) 5,
                "Great product!",
                "20250627",
                customer,
                null
        );

        Customer customerWithRelations = CustomerFactory.createCustomer(
                "John",
                "Doe",
                "mengezi@gmail.com",
                "0781234567",
                "securePassword123",
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                null
        );

        Customer saved = customerService.create(customerWithRelations);
        assertNotNull(saved);
        System.out.println("Customer With Relationships: " + saved);
    }

    @Test
    void e_getAll() {
        assertNotNull(customerService.getAll());
        System.out.println("All Customers: " + customerService.getAll());
    }
}