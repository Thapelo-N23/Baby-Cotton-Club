/**
 * BabyCottonClub
 * Product.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date : 18 May 2025
 */

package za.ac.cput.factory;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Order;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CustomerFactoryTest {

    private static Address address1 = AddressFactory.createAddress(
            "Bush St",
            (short) 123,
            "Soweto",
            "Johannesburg",
            (short) 1634,
            "Gauteng",
            null // Customer will be set later
    );

    private static Address address2 = AddressFactory.createAddress(
            "Bush St",
            (short) 123,
            "Soweto",
            "Johannesburg",
            (short) 1634,
            "Gauteng",
            null // Customer will be set later
    );

    private static Customer customer = CustomerFactory.createCustomer(
            "John",
            "Doe",
            "mengezi@gmail.com",
            "0781234567",
            Arrays.asList(address1, address2),
            Collections.emptyList()
    );
    private static Order order1 = OrderFactory.createOrder(
            "20250518",
            250.00,
            2,
            20.00,
          null
    );

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
