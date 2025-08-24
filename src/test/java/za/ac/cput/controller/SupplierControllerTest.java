
package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import za.ac.cput.domain.Supplier;
import za.ac.cput.factory.SupplierFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SupplierControllerTest {

    private Supplier supplier;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/supplier";
    }

    @BeforeAll
    void setUp() {
        // Create supplier using factory, no relationships
        supplier = SupplierFactory.createSupplier(
                "Baby Doodles",
                "0213444667",
                null
        );

        ResponseEntity<Supplier> response = restTemplate.postForEntity(
                getBaseUrl() + "/create",
                supplier,
                Supplier.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode(), "Failed to create supplier in setup");
        supplier = response.getBody();
        assertNotNull(supplier, "Supplier creation returned null");
        assertNotNull(supplier.getSupplierId(), "Supplier ID is null after creation");
        System.out.println("Setup Supplier: " + supplier);
    }

    @Test
    void a_create() {
        assertNotNull(supplier);
        assertNotNull(supplier.getSupplierId());
        System.out.println("Created Supplier: " + supplier);
    }

    @Test
    void b_read() {
        String url = getBaseUrl() + "/read/" + supplier.getSupplierId();
        ResponseEntity<Supplier> response = restTemplate.getForEntity(url, Supplier.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(supplier.getSupplierId(), response.getBody().getSupplierId());
        System.out.println("Read Supplier: " + response.getBody());
    }

    @Test
    void c_update() {
        Supplier updatedSupplier = new Supplier.Builder()
                .copy(supplier)
                .setContactDetails("newemail@supplier.com")
                .build();

        HttpEntity<Supplier> request = new HttpEntity<>(updatedSupplier);
        String url = getBaseUrl() + "/update";
        ResponseEntity<Supplier> response = restTemplate.exchange(url, HttpMethod.PUT, request, Supplier.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("newemail@supplier.com", response.getBody().getContactDetails());

        supplier = response.getBody();
        System.out.println("Updated Supplier: " + supplier);
    }

    @Test
    void d_getAll() {
        String url = getBaseUrl() + "/getall";
        ResponseEntity<Supplier[]> response = restTemplate.getForEntity(url, Supplier[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        System.out.println("All Suppliers count: " + response.getBody().length);
    }
}
