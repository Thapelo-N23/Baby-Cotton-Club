package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Category;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.Supplier;
import za.ac.cput.factory.ProductFactory;
import za.ac.cput.factory.ReviewFactory;
import za.ac.cput.factory.SupplierFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductControllerTest {

    private Product product;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/product";
    }

    @BeforeAll
    void setUp() {
        // Build dummy objects (no need to call other controllers)
        Category category = new Category.Builder()
                .setCategoryName("Baby Clothes")
                .build();

        Review review = ReviewFactory.createReview((short) 4, "Great service!", "20250503", null, null);

        Supplier supplier = SupplierFactory.createSupplier("SnuggleBabies Clothing Co.", "0211234567", null);

        product = ProductFactory.createProduct("ZARA", "white", (short) 19, "out of stock", review, supplier);

        // Assign category
        product = new Product.Builder()
                .copy(product)
                .setCategory(category)
                .build();

        // Save product via ProductController
        ResponseEntity<Product> response = restTemplate.postForEntity(getBaseUrl() + "/create", product, Product.class);

        // Debug
        System.out.println("POST /create status: " + response.getStatusCode());
        System.out.println("Generated Product ID: " + response.getBody().getProductId());

        assertEquals(HttpStatus.OK, response.getStatusCode(), "Product creation failed");
        assertNotNull(response.getBody(), "Response body is null after product creation");
        assertTrue(response.getBody().getProductId() > 0, "Product ID not generated correctly");

        product = response.getBody();
    }

    @Test
    void a_create() {
        assertNotNull(product);
        System.out.println("Created Product: " + product);
    }

    @Test
    void b_read() {
        ResponseEntity<Product> response = restTemplate.getForEntity(
                getBaseUrl() + "/read/" + product.getProductId(),
                Product.class
        );
        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null || response.getBody().getProductId() == 0) {
            fail("Product read returned " + response.getStatusCode() + " with body: " + response.getBody());
        }
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(product.getProductId(), response.getBody().getProductId());
        System.out.println("Read Product: " + response.getBody());
    }

    @Test
    void c_update() {
        Product updatedProduct = new Product.Builder()
                .copy(product)
                .setInStock("In Stock")
                .build();

        HttpEntity<Product> request = new HttpEntity<>(updatedProduct);
        ResponseEntity<Product> response = restTemplate.exchange(getBaseUrl() + "/update", HttpMethod.PUT, request, Product.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("In Stock", response.getBody().getInStock());

        product = response.getBody();
        System.out.println("Updated Product: " + product);
    }

    @Test
    void d_getAll() {
        String url = getBaseUrl() + "/getall";
        ResponseEntity<Product[]> response = restTemplate.getForEntity(url, Product[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);

        List<Product> products = List.of(response.getBody());
        System.out.println("All Products count: " + products.size());
    }
}
