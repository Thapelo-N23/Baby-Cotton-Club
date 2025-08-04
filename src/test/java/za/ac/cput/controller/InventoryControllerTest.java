/*
package za.ac.cput.controller;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.Inventory;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Supplier;
import za.ac.cput.factory.InventoryFactory;
import za.ac.cput.factory.ProductFactory;
import za.ac.cput.factory.SupplierFactory;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InventoryControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String url = "http://localhost:8080";
    private static Inventory inventory;
    private static Product product;
    private static Supplier supplier;

    @BeforeAll
    public static void setUp() {
        product = ProductFactory.createProduct(
                90087L,
                "Baby Onesie",
                "White cotton",
                (short) 50,
                "In stock"
        );

        supplier = SupplierFactory.createSupplier(
                "Tiny Tots",
                "supply@tinytots.com",
                101
        );

        inventory = InventoryFactory.createInventory(
                LocalDate.now().toString(),
                "100 units",
                List.of(supplier),
                product
        );
    }

    @Test
    @Order(1)
    void createInventory() {
        String createUrl = url + "/inventory";
        ResponseEntity<Inventory> postResponse = restTemplate.postForEntity(
                createUrl,
                inventory,
                Inventory.class
        );

        Inventory createdInventory = postResponse.getBody();
        assert createdInventory != null;
        assertNotNull(inventory);
        System.out.println("Inventory created: " + createdInventory);
    }

    @Test
    @Order(2)
    void readInventory() {
        int inventoryId = inventory.getInventoryId();
        String readUrl = url + "/inventory/" + inventoryId;
        System.out.println("Reading inventory at: " + readUrl);

        ResponseEntity<Inventory> readResponse = restTemplate.getForEntity(
                readUrl,
                Inventory.class
        );

        Inventory readInventory = readResponse.getBody();
        assert readInventory != null;
        System.out.println("Inventory read: " + readInventory);
    }

    @Test
    @Order(3)
    void updateInventory() {
        String updateUrl = url + "/inventory/";
        System.out.println("Updating inventory at: " + updateUrl);

        Inventory updatedInventory = new Inventory.Builder()
                .copy(inventory)
                .setStockAdded("150 units")
                .build();

        HttpEntity<Inventory> request = new HttpEntity<>(updatedInventory);
        ResponseEntity<Inventory> response = restTemplate.exchange(
                updateUrl,
                HttpMethod.POST,
                request,
                Inventory.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Inventory result = response.getBody();
        assert result != null;
        System.out.println("Inventory updated: " + result);
    }

    @Test
    @Order(4)
    void getAllInventories() {
        String allUrl = url + "/inventory";
        System.out.println("Getting all inventories at: " + allUrl);

        HttpEntity<String> request = new HttpEntity<>(null);
        ResponseEntity<String> response = restTemplate.exchange(
                allUrl,
                HttpMethod.GET,
                request,
                String.class
        );

        System.out.println("All inventories: " + response.getBody());
    }
}
 */