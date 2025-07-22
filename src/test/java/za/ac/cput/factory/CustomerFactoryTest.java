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
            "John",
            "Doe",
            "mengezi@gmail.com",
            "0781234567",
            "Main St",
            (short)1234,
            "Cape Town",
            "South Africa",
            (short)8000,
            "Western Cape");




    @Test
    void createCustomer() {
        assertNotNull(customer);
        System.out.println(customer.toString());
    }
}