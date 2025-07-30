package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Order;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CustomerFactoryTest {

    // Create Address objects with dummy data
    public static Address address1 = AddressFactory.createAddress(
            "Bush St",
            (short) 123,
            "Soweto",
            "Johannesburg",
            (short) 1634,
            "Gauteng",
            null // Customer will be set later if needed
    );

    public static Address address2 = AddressFactory.createAddress(
            "Bush St",
            (short) 456,
            "Soweto",
            "Johannesburg",
            (short) 1634,
            "Gauteng",
            null

    );

    // Create a customer without orders
    private static Customer customer = CustomerFactory.createCustomer(
            "John",
            "Doe",
            "mengezi@gmail.com",
            "0781234567",
            Arrays.asList(address1, address2),
            Collections.emptyList()
    );

    // Create an order
    public static Order order1 = OrderFactory.createOrder(
            "20250518",  // Use valid date format consistent with your OrderFactory
            250.00,
            new java.util.ArrayList<>(),  // empty orderLines list (adjust as needed)
            customer                      // link customer to order
    );

    // Create a customer with orders
    private static Customer customerWithOrders = CustomerFactory.createCustomer(
            "John",
            "Doe",
            "mengezi@gmail.com",
            "0781234567",
            Arrays.asList(address1, address2),
            Arrays.asList(order1)
    );

    @Test
    void createCustomer() {
        assertNotNull(customer);
        System.out.println(customer.toString());
    }

    @Test
    void createCustomerWithOrders() {
        assertNotNull(customerWithOrders);
        assertFalse(customerWithOrders.getOrders().isEmpty());
        System.out.println(customerWithOrders.toString());
    }
}
