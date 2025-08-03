/*
package za.ac.cput.service.impl;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.service.IAddressService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressServiceTest {

    @Autowired
    private IAddressService service;

    private static Customer customer = CustomerFactory.createCustomer(
            "John", "Doe", "mengezi@gmail.com", "0781234567",
            Collections.emptyList(), Collections.emptyList(), Collections.emptyList()
    );
    private static Address address;

    @Test
    @Order(1)
    void create() {
        address = AddressFactory.createAddress(
                "Bush St", (short) 123, "Soweto", "Johannesburg", (short) 1634, "Gauteng", customer
        );
        Address created = service.create(address);
        assertNotNull(created);
        address = created;
        System.out.println("Created Address: " + address);
    }

    @Test
    @Order(2)
    void read() {
        Address read = service.read(address.getAddressId());
        assertNotNull(read);
        System.out.println("Read Address: " + read);
    }

    @Test
    @Order(3)
    void update() {
        Address updated = new Address.Builder()
                .copy(address)
                .setStreetName("Updated Bush St")
                .setCity("Updated Soweto")
                .build();
        Address result = service.update(updated);
        assertNotNull(result);
        address = result;
        System.out.println("Updated Address: " + result);
    }

    @Test
    @Order(4)
    void getAll() {
        assertNotNull(service.getAll());
        System.out.println("All Addresses: " + service.getAll());
    }
}
*/