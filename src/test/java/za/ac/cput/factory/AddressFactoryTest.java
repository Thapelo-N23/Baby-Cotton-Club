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

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class AddressFactoryTest {

    private static  Customer customer = CustomerFactory.createCustomer(
            "John",
            "Doe",
            "mengezi@gmail.com",
            "0781234567",
            "securePassword123",
            Collections.emptyList(),
            Collections.emptyList(),
            Collections.emptyList()
    );

    @Test
    void createAddress() {
        Address address = AddressFactory.createAddress(
                "Bush St",
                (short) 123,
                "Soweto",
                "Johannesburg",
                (short) 1634,
                "Gauteng",
                customer
        );

        assertNotNull(address);
        System.out.println(address);
    }

    @Test
    void createAddressWithInvalidPostalCode() {
        Address invalidAddress = AddressFactory.createAddress(
                "Main St",
                (short) 10,
                "Pretoria",
                "Pretoria",
                (short) -1, // Invalid postal code
                "Gauteng",
                customer
        );

        assertNull(invalidAddress, "Address with invalid postal code should be null");
        System.out.println(invalidAddress);
    }
}
