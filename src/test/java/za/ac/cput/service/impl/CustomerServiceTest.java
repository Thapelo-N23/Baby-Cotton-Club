
package za.ac.cput.service.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;

import za.ac.cput.factory.*;
import za.ac.cput.service.ICustomerService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static za.ac.cput.factory.CustomerFactoryTest.shipment;
import static za.ac.cput.factory.PaymentFactoryTest.orderLines;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerServiceTest {

    @Autowired
    private ICustomerService customerService;


    private static Address address;
    private static CustomerOrder order;
    private static Review review;
    private static List<CartItem> cartItems = new ArrayList<>();

    private static Cart cart = CartFactory.createCart(null, cartItems);

    private static Customer customer = CustomerFactory.createCustomer(
            "John",
            "Doe",
            "mengezi@gmail.com",
            "0781234567",
            "securePassword123",
            Arrays.asList(address),
            Arrays.asList(order) ,
            Arrays.asList(review),
            cart

    );
    @Test
    @Order(1)
    void create() {


        Customer created = customerService.create(customer);
        assertNotNull(created);
        customer = created; // Save for later tests
        System.out.println("Created Customer: " + customer);
    }

    @Test
    @Order(2)
    void read() {

        Customer readCustomer = customerService.read(customer.getCustomerId());
        assertNotNull(readCustomer);
        System.out.println("Read Customer: " + readCustomer);
    }

    @Test
    @Order(3)
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
    @Order(4)
    void createCustomerWithRelationships() {
        // Create relationships
        address = AddressFactory.createAddress(
                "Bush St", (short) 123, "Soweto", "Johannesburg", (short) 1634, "Gauteng", customer
        );

        order = CustomerOrderFactory.createCustomerOrder(
                "20250729",
                99.99,
                orderLines,
                customer,
                null);

        review = ReviewFactory.createReview(
                (short) 4, "Great service!", "20250503", customer, null
        );

        Customer customerWithRelations = CustomerFactory.createCustomer(
                "John",
                "Doe",
                "mengezi@gmail.com",
                "0781234567",
                "securePassword123",
                Arrays.asList(address),
                Arrays.asList(order),
                Arrays.asList(review),
                cart


        );

        Customer saved = customerService.create(customerWithRelations);
        assertNotNull(saved);
        System.out.println("Customer With Relationships: " + saved);
    }

    @Test
    @Order(5)
    void getAll() {
        assertNotNull(customerService.getAll());
        System.out.println("All Customers: " + customerService.getAll());
    }


}
