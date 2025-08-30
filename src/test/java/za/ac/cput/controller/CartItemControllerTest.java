package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.CartFactory;
import za.ac.cput.factory.CartItemFactory;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.factory.ProductFactory;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CartItemControllerTest {

    private CartItem cartItem;
    private Cart cart;
    private Product product;
    private Customer customer;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/cartItem";
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

        product = ProductFactory.createProduct(
                "Baby Blanket",
                "Soft cotton blanket",
                (short)150,
                "yes",
                null,
                null
        );

        cartItem = CartItemFactory.createCartItem(cart, product, 5);

        String url = getBaseUrl() + "/create";
        ResponseEntity<CartItem> response = restTemplate.postForEntity(url, cartItem, CartItem.class);
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Failed to create CartItem in setup");
        cartItem = response.getBody();
        assertNotNull(cartItem);
        System.out.println("Test server running on: http://localhost:" + port);
    }

    @Test
    void a_createCartItem() {
        assertNotNull(cartItem);
        assertNotNull(cartItem.getCartItemId());
        System.out.println("Created CartItem: " + cartItem);
    }

    @Test
    void b_readCartItem() {
        String url = getBaseUrl() + "/read/" + cartItem.getCartItemId();
        ResponseEntity<CartItem> response = restTemplate.getForEntity(url, CartItem.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(cartItem.getCartItemId(), response.getBody().getCartItemId());
        System.out.println("Read CartItem: " + response.getBody());
    }

    @Test
    void c_updateCartItem() {
        CartItem updated = new CartItem.Builder()
                .copy(cartItem)
                .setQuantity(7)
                .build();

        HttpEntity<CartItem> request = new HttpEntity<>(updated);
        String url = getBaseUrl() + "/update";
        ResponseEntity<CartItem> response = restTemplate.exchange(url, HttpMethod.PUT, request, CartItem.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        cartItem = response.getBody(); // update reference
        System.out.println("Updated CartItem: " + cartItem);
    }

    @Test
    void d_getAllCartItems() {
        String url = getBaseUrl() + "/findAll";
        ResponseEntity<CartItem[]> response = restTemplate.getForEntity(url, CartItem[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        System.out.println("All CartItems: " + response.getBody().length);
    }
}
