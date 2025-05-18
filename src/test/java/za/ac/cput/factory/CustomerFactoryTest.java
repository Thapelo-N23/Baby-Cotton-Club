/**
 * BabyCottonClub
 * Product.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date : 18 May 2025
 */

package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Customer;

import static org.junit.jupiter.api.Assertions.*;

class CustomerFactoryTest {
    private CustomerFactory factory;
    private static Customer customer = CustomerFactory.createCustomer(
            123456,
            "John",
            "Doe",
            "mengezi@gmail.com",
            "0781234567");


    @Test
    void createCustomer() {
        assertNotNull(customer);
        System.out.println(customer.toString());

    }
    @Test
    void createCustomerWithInvalidEmail() {
        Customer invalidCustomer = CustomerFactory.createCustomer(
                123457,
                "Jane",
                "Smith",
                "invalid-email",
                "0787654321"
        );
        assertNull(invalidCustomer, "Customer with invalid email should be null");
        System.out.println(invalidCustomer);
    }
}
