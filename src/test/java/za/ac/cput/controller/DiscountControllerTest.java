
package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import za.ac.cput.domain.Discount;
import za.ac.cput.factory.DiscountFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DiscountControllerTest {

    private Discount discount;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/discount";
    }

    @BeforeAll
    void setUp() {
        discount = DiscountFactory.createDiscount(
                "Black Friday",
                "Percentage",
                "25%",
                "2025-11-22",
                "2025-11-25",
                null
        );

        String url = getBaseUrl() + "/create";
        ResponseEntity<Discount> response = restTemplate.postForEntity(url, discount, Discount.class);
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Failed to create discount");
        discount = response.getBody();
        assertNotNull(discount);
        assertNotEquals(0, discount.getDiscountId());
    }

    @Test
    void a_create() {
        assertNotNull(discount);
        System.out.println("Created Discount: " + discount);
    }

    @Test
    void b_read() {
        String url = getBaseUrl() + "/read/" + discount.getDiscountId();
        ResponseEntity<Discount> response = restTemplate.getForEntity(url, Discount.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(discount.getDiscountId(), response.getBody().getDiscountId());
        System.out.println("Read Discount: " + response.getBody());
    }

    @Test
    void c_update() {
        Discount updatedDiscount = new Discount.Builder()
                .copy(discount)
                .setDiscountValue("30%")
                .build();

        HttpEntity<Discount> request = new HttpEntity<>(updatedDiscount);
        String url = getBaseUrl() + "/update";
        ResponseEntity<Discount> response = restTemplate.exchange(url, HttpMethod.PUT, request, Discount.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("30%", response.getBody().getDiscountValue());

        discount = response.getBody();
        System.out.println("Updated Discount: " + discount);
    }

    @Test
    void d_findAll() {
        String url = getBaseUrl() + "/getall";
        ResponseEntity<Discount[]> response = restTemplate.getForEntity(url, Discount[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        System.out.println("All Discounts: " + response.getBody().length);
    }
}
