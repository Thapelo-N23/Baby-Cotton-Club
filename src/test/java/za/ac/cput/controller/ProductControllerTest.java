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

    private List<Product> products = new java.util.ArrayList<>();

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/products";
    }

    @BeforeAll
    void setUp() {
        Category category = new Category.Builder()
                .setCategoryName("Baby Clothes")
                .build();
        Review review = ReviewFactory.createReview((short) 4, "Great service!", "20250503", null, null);
        Supplier supplier = SupplierFactory.createSupplier("SnuggleBabies Clothing Co.", "0211234567", null);

        String[] names = {"Cotton Onesie", "Baby Blanket", "Tiny Socks", "Soft Bib", "Sleep Suit", "Play Mat", "Hooded Towel", "Booties", "Swaddle Wrap", "Crib Sheet"};
        short[] prices = {120, 250, 60, 45, 180, 300, 90, 70, 150, 110};
        for (int i = 0; i < 10; i++) {
            Product p = ProductFactory.createProduct(
                names[i],
                "Color" + (i+1),
                prices[i],
                "available",
                review,
                supplier
            );
            p = new Product.Builder().copy(p).setCategory(category).build();
            ResponseEntity<Product> response = restTemplate.postForEntity(getBaseUrl() + "/create", p, Product.class);
            System.out.println("POST /create status for " + names[i] + ": " + response.getStatusCode());
            assertEquals(HttpStatus.OK, response.getStatusCode(), "Product creation failed for " + names[i]);
            assertNotNull(response.getBody(), "Response body is null after product creation for " + names[i]);
            assertTrue(response.getBody().getProductId() > 0, "Product ID not generated correctly for " + names[i]);
            products.add(response.getBody());
        }
        product = products.get(0);
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
        ResponseEntity<Product> response = restTemplate.exchange(
                getBaseUrl() + "/update/" + product.getProductId(),
                HttpMethod.PUT,
                request,
                Product.class
        );

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
