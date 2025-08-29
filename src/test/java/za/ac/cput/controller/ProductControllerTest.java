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
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Supplier;
import za.ac.cput.factory.ProductFactory;
import za.ac.cput.factory.SupplierFactory;

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
        Supplier supplier = SupplierFactory.createSupplier(
                "SnuggleBabies Clothing Co.",
                "0211234567",
                null
        );
        product = ProductFactory.createProduct(
                "ZARA",
                "white",
                (short) 19,
                "out of stock",
                null,
                supplier
        );
        ResponseEntity<Product> response = restTemplate.postForEntity(
                getBaseUrl() + "/create",
                product,
                Product.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        product = response.getBody();
        assertNotNull(product);
        assertNotNull(product.getProductId());
        assertNotNull(product.getSupplier());
        System.out.println("Created Product (setup): " + product);
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
                getBaseUrl() + "/update",
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
        System.out.println("All Products count: " + response.getBody().length);
    }
}
