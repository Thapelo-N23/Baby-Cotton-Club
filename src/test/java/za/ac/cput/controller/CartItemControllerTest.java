/**
 * BabyCottonClub
 * CartItemControllerTest.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date : 30 August 2025
 */

package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import za.ac.cput.domain.*;
import za.ac.cput.factory.ProductFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CartItemControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private CartItem cartItem;
    private Product product;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/cartItem";
    }

    @BeforeEach
    void setUp() {
        product = ProductFactory.createProduct(
            "Test Product",
            "Blue",
            (short) 100,
            "yes",
            null,
            null
        );
        cartItem = new CartItem.Builder()
                .setProduct(product)
                .setQuantity(5)
                .build();
    }

    @Test
    @Order(1)
    void testCreate() {
        String url = getBaseUrl() + "/create";
        ResponseEntity<CartItem> response = restTemplate.postForEntity(url, cartItem, CartItem.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        cartItem = response.getBody();
        assertEquals(5, cartItem.getQuantity());
    }

    @Test
    @Order(2)
    void testRead() {
        String url = getBaseUrl() + "/read/" + cartItem.getCartItemId();
        ResponseEntity<CartItem> response = restTemplate.getForEntity(url, CartItem.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(cartItem.getCartItemId(), response.getBody().getCartItemId());
    }

    @Test
    @Order(3)
    void testUpdate() {
        CartItem updated = new CartItem.Builder()
                .copy(cartItem)
                .setQuantity(8)
                .build();
        String url = getBaseUrl() + "/update";
        HttpEntity<CartItem> request = new HttpEntity<>(updated);
        ResponseEntity<CartItem> response = restTemplate.exchange(url, HttpMethod.PUT, request, CartItem.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(8, response.getBody().getQuantity());
        cartItem = response.getBody();
    }


    @Test
    @Order(5)
    void testGetAll() {
        String url = getBaseUrl() + "/getall";
        ResponseEntity<CartItem[]> response =
                restTemplate.getForEntity(url, CartItem[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().length >= 1);
    }
}
