/**
 * BabyCottonClub
 * Product.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date :  2025
 */



package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static za.ac.cput.factory.ReviewFactoryTest.product1;

public class CustomerFactoryTest {


    private static List<CartItem> cartItems = new ArrayList<>();

    private static Cart cart = CartFactory.createCart(null, cartItems);
    // Create a customer
    private static Customer customer = CustomerFactory.createCustomer(
            "John",
            "Doe",
            "mengezi@gmail.com",
            "0781234567",
            "securePassword123",
            Collections.emptyList(),
            Collections.emptyList(),
            Collections.emptyList(),
            cart

    );
    //create an address
    public static Address address1 = AddressFactory.createAddress(
            "Bush St",
            (short) 123,
            "Soweto",
            "Johannesburg",
            (short) 1634,
            "Gauteng",
            customer
    );
    public static Shipment shipment = ShipmentFactory.createShipment("DHL", "OUT OF STOCK", 23,null);

    public static CustomerOrder customerOrder1 = CustomerOrderFactory.createCustomerOrder(
            "20250518",
            250.00,
            new java.util.ArrayList<>(),
            customer,
            shipment
    );



    // Create a review
    public static Review review = ReviewFactory.createReview(
            (short) 4,
            "Great service!",
            "20250503",
            customer,
            product1);


    // Create a customer with orders, address  and reviews
    private static Customer customerWithRelationships = CustomerFactory.createCustomer(
            "John",
            "Doe",
            "mengezi@gmail.com",
            "0781234567",
            "securePassword123",
            Arrays.asList(address1),
            Arrays.asList(customerOrder1),
            Arrays.asList(review),
            cart
    );

    @Test
    void createCustomer() {
        assertNotNull(customer);
        System.out.println(customer.toString());
    }

    @Test
    void createCustomerWithOrders() {
        assertNotNull(customerWithRelationships);
        assertFalse(customerWithRelationships.getOrders().isEmpty());
        System.out.println(customerWithRelationships.toString());
    }
}
