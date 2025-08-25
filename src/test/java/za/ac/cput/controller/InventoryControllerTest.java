/*
 * InventoryControllerTest.java
 * Author: Onako Ntsaluba (230741754)
 * Date: 25 May 2025
 */

package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import za.ac.cput.domain.Inventory;
import za.ac.cput.domain.Product;
import za.ac.cput.factory.InventoryFactory;
import za.ac.cput.factory.ProductFactory;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InventoryControllerTest {

    private Inventory inventory;
    private Product product;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/inventory";
    }

    @BeforeAll
    void setUp() {
        // Create minimal Product (required for Inventory)
        product = ProductFactory.createProduct(
                "Test Product",
                "Test Description",
                (short)10,
                "Yes",
                null
        );

        // Create Inventory without suppliers (no relationships)
        inventory = InventoryFactory.createInventory(
                LocalDate.now().toString(),
                "20 units",
                null,
                product // Product is mandatory
        );

        // POST to create Inventory
        ResponseEntity<Inventory> response = restTemplate.postForEntity(
                getBaseUrl() + "/create",
                inventory,
                Inventory.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        inventory = response.getBody();
        assertNotNull(inventory);
        assertNotNull(inventory.getInventoryId());
        System.out.println("Created Inventory (setup): " + inventory);
    }

    @Test
    void a_create() {
        assertNotNull(inventory);
        System.out.println("Created Inventory: " + inventory);
    }

    @Test
    void b_read() {
        ResponseEntity<Inventory> response = restTemplate.getForEntity(
                getBaseUrl() + "/read/" + inventory.getInventoryId(),
                Inventory.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(inventory.getInventoryId(), response.getBody().getInventoryId());
        System.out.println("Read Inventory: " + response.getBody());
    }

    @Test
    void c_update() {
        Inventory updatedInventory = new Inventory.Builder()
                .copy(inventory) // ensures all relationships are copied
                .setStockAdded("30 units") // only change stockAdded
                .build();

        HttpEntity<Inventory> request = new HttpEntity<>(updatedInventory);

        ResponseEntity<Inventory> response = restTemplate.exchange(
                getBaseUrl() + "/update",
                HttpMethod.PUT,
                request,
                Inventory.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("30 units", response.getBody().getStockAdded());

        inventory = response.getBody(); // keep latest
        System.out.println("Updated Inventory: " + inventory);
    }

    @Test
    void d_getAll() {
        ResponseEntity<Inventory[]> response = restTemplate.getForEntity(
                getBaseUrl() + "/getall",
                Inventory[].class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0, "No inventories found");
        System.out.println("All Inventories: " + Arrays.toString(response.getBody()));
    }
}
