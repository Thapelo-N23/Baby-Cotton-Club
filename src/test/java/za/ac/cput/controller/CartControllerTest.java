/**
 * BabyCottonClub
 * CartItemControllerTest.java
 * Author : Onako Ntsaluba - 230741754
 */

package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.CartFactory;
import za.ac.cput.factory.CustomerFactory;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CartControllerTest {

    private Cart cart;
    private Customer customer;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/cart";
    }

    @BeforeAll
    void setUp() {

        customer = CustomerFactory.createCustomer(
                "John",
                "Doe",
                "john.doe@email.com",
                "1234567890",
                "password123",
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                null
        );


        cart = CartFactory.createCart(customer, Collections.emptyList());

        String url = getBaseUrl() + "/create";
        ResponseEntity<Cart> response = restTemplate.postForEntity(url, cart, Cart.class);
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Failed to create cart in setup");
        cart = response.getBody();
        assertNotNull(cart);
        System.out.println("Test server running on: http://localhost:" + port);
    }

    @Test
    void a_createCart() {
        assertNotNull(cart);
        assertNotNull(cart.getCartId());
        System.out.println("Created Cart: " + cart);
    }

    @Test
    void b_readCart() {
        String url = getBaseUrl() + "/read/" + cart.getCartId();
        ResponseEntity<Cart> response = restTemplate.getForEntity(url, Cart.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(cart.getCartId(), response.getBody().getCartId());
        System.out.println("Read Cart: " + response.getBody());
    }

    @Test
    void c_updateCart() {
        Cart updated = new Cart.Builder()
                .copy(cart)
                .build(); // you can add updated fields if needed

        HttpEntity<Cart> request = new HttpEntity<>(updated);
        String url = getBaseUrl() + "/update";
        ResponseEntity<Cart> response = restTemplate.exchange(url, HttpMethod.PUT, request, Cart.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        cart = response.getBody(); // update reference
        System.out.println("Updated Cart: " + cart);
    }

    @Test
    void d_getAllCarts() {
        String url = getBaseUrl() + "/findAll";
        ResponseEntity<Cart[]> response = restTemplate.getForEntity(url, Cart[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        System.out.println("All Carts: " + response.getBody().length);
    }
}
