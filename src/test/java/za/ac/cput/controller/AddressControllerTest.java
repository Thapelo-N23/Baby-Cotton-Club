///**
// * BabyCottonClub
// * Product.java
// * Author : Mengezi Junior Ngwenya - 230023967
// * Date :  2025
// */
//
//package za.ac.cput.controller;
//
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.*;
//import za.ac.cput.domain.Address;
//import za.ac.cput.domain.Customer;
//import za.ac.cput.factory.AddressFactory;
//import za.ac.cput.factory.CustomerFactory;
//import za.ac.cput.repository.CustomerRepository;
//
//import java.util.Collections;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestMethodOrder(MethodOrderer.MethodName.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class AddressControllerTest {
//
//    private Address address;
//    private Customer customer;
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    private String getBaseUrl() {
//        return "http://localhost:" + port + "/address";
//    }
//
//    @BeforeAll
//    void setUp() {
//
//        address = AddressFactory.createAddress(
//                "Main Street",
//                (short) 42,
//                "Khuma",
//                "Klerksdorp",
//                (short) 2600,
//                "North West",
//                customer
//        );
//
//        String url = getBaseUrl() + "/create";
//        ResponseEntity<Address> response = restTemplate.postForEntity(url, address, Address.class);
//        assertEquals(HttpStatus.OK, response.getStatusCode(), "Failed to create address in setup");
//        address = response.getBody();
//        assertNotNull(address);
//        System.out.println("Test server running on: http://localhost:" + port);
//
//    }
//
//    @Test
//    void a_create() {
//        assertNotNull(address);
//        assertNotNull(address.getAddressId());
//        System.out.println("Created Address: " + address);
//    }
//
//    @Test
//    void b_read() {
//        String url = getBaseUrl() + "/read/" + address.getAddressId();
//        ResponseEntity<Address> response = restTemplate.getForEntity(url, Address.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(address.getAddressId(), response.getBody().getAddressId());
//        System.out.println("Read Address: " + response.getBody());
//    }
//
//    @Test
//    void c_update() {
//        Address updated = new Address.Builder()
//                .copy(address)
//                .setCity("Updated City")
//                .setStreetName("Updated Street")
//                .build();
//
//        HttpEntity<Address> request = new HttpEntity<>(updated);
//        String url = getBaseUrl() + "/update";
//        ResponseEntity<Address> response = restTemplate.exchange(url, HttpMethod.PUT, request, Address.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals("Updated City", response.getBody().getCity());
//
//        address = response.getBody(); // update reference
//        System.out.println("Updated Address: " + address);
//    }
//
//    @Test
//    void d_getAll() {
//        String url = getBaseUrl() + "/getall";
//        ResponseEntity<Address[]> response = restTemplate.getForEntity(url, Address[].class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertTrue(response.getBody().length > 0);
//        System.out.println("All Addresses: " + response.getBody().length);
//    }
//}
