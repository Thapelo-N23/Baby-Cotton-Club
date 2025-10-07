///**
// * BabyCottonClub
// * CartItemControllerTest.java
// * Author : Onako Ntsaluba - 230741754
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
//
//import za.ac.cput.domain.CartItem;
//import za.ac.cput.domain.Product;
//import za.ac.cput.factory.ProductFactory;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestMethodOrder(MethodOrderer.MethodName.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class CartItemControllerTest {
//
//    private CartItem cartItem;
//    private Product product;
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    private String getBaseUrl() {
//        return "http://localhost:" + port + "/api/cartItem";
//    }
//
//    @BeforeAll
//    void setUp() {
//
//        product = ProductFactory.createProduct(
//                "Test Product",
//                "Blue",
//                (short) 100,
//                "yes",
//                null,
//                null
//        );
//
//        cartItem = new CartItem.Builder()
//                .setProduct(product)
//                .setQuantity(5)
//                .build();
//
//        ResponseEntity<CartItem> response = restTemplate.postForEntity(
//                getBaseUrl() + "/create",
//                cartItem,
//                CartItem.class
//        );
//
//        assertEquals(HttpStatus.OK, response.getStatusCode(), "Failed to create cart item in setup");
//        cartItem = response.getBody();
//        assertNotNull(cartItem, "CartItem creation returned null");
//        assertNotNull(cartItem.getCartItemId(), "CartItem ID is null after creation");
//        System.out.println("Setup CartItem: " + cartItem);
//    }
//
//    @Test
//    void a_create() {
//        assertNotNull(cartItem);
//        assertNotNull(cartItem.getCartItemId());
//        assertEquals(5, cartItem.getQuantity());
//        System.out.println("Created CartItem: " + cartItem);
//    }
//
//    @Test
//    void b_read() {
//        String url = getBaseUrl() + "/read/" + cartItem.getCartItemId();
//        ResponseEntity<CartItem> response = restTemplate.getForEntity(url, CartItem.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(cartItem.getCartItemId(), response.getBody().getCartItemId());
//        System.out.println("Read CartItem: " + response.getBody());
//    }
//
//    @Test
//    void c_update() {
//        CartItem updatedCartItem = new CartItem.Builder()
//                .copy(cartItem)
//                .setQuantity(8)
//                .build();
//
//        HttpEntity<CartItem> request = new HttpEntity<>(updatedCartItem);
//        String url = getBaseUrl() + "/update";
//        ResponseEntity<CartItem> response = restTemplate.exchange(url, HttpMethod.PUT, request, CartItem.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(8, response.getBody().getQuantity());
//
//        cartItem = response.getBody(); // update local reference
//        System.out.println("Updated CartItem: " + cartItem);
//    }
//
//    @Test
//    void d_getAll() {
//        String url = getBaseUrl() + "/findAll";
//        ResponseEntity<CartItem[]> response = restTemplate.getForEntity(url, CartItem[].class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertTrue(response.getBody().length > 0);
//        System.out.println("All CartItems count: " + response.getBody().length);
//    }
//}