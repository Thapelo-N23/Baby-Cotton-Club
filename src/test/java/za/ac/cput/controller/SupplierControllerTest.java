//package za.ac.cput.controller;
//
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.*;
//import za.ac.cput.domain.Inventory;
//import za.ac.cput.domain.Product;
//import za.ac.cput.domain.Supplier;
//import za.ac.cput.factory.InventoryFactory;
//import za.ac.cput.factory.ProductFactory;
//import za.ac.cput.factory.SupplierFactory;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestMethodOrder(MethodOrderer.MethodName.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class SupplierControllerTest {
//
//    private Supplier supplier;
//    private Inventory inventory;
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    private String getBaseUrl() {
//        return "http://localhost:" + port + "/supplier";
//    }
//
//    @BeforeAll
//    void setUp() {
//        Product product = ProductFactory.createProduct("Teddy Bear", "Baby Blue", "250", "true");
//        inventory = InventoryFactory.createInventory("2025-08-01", "10 units", Collections.emptyList(), product);
//
//        supplier = SupplierFactory.createSupplier("Babby doodles", "BabbyDoodles@supplies.com", inventory);
//
//        String url = getBaseUrl() + "/create";
//        ResponseEntity<Supplier> response = restTemplate.postForEntity(url, supplier, Supplier.class);
//        assertEquals(HttpStatus.OK, response.getStatusCode(), "Failed to create supplier");
//        supplier = response.getBody();
//        assertNotNull(supplier);
//        assertNotNull(supplier.getSupplierId());
//    }
//
//    @Test
//    void a_create() {
//        assertNotNull(supplier);
//        System.out.println("Created Supplier: " + supplier);
//    }
//
//    @Test
//    void b_read() {
//        String url = getBaseUrl() + "/read/" + supplier.getSupplierId();
//        ResponseEntity<Supplier> response = restTemplate.getForEntity(url, Supplier.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(supplier.getSupplierId(), response.getBody().getSupplierId());
//        System.out.println("Read Supplier: " + response.getBody());
//    }
//
//    @Test
//    void c_update() {
//        Supplier updatedSupplier = new Supplier.Builder()
//                .copy(supplier)
//                .setContactDetails("newemail@supplier.com")
//                .build();
//
//        HttpEntity<Supplier> request = new HttpEntity<>(updatedSupplier);
//        String url = getBaseUrl() + "/update";
//        ResponseEntity<Supplier> response = restTemplate.exchange(url, HttpMethod.PUT, request, Supplier.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals("newemail@supplier.com", response.getBody().getContactDetails());
//
//        supplier = response.getBody();
//        System.out.println("Updated Supplier: " + supplier);
//    }
//
//    @Test
//    void d_getAll() {
//        String url = getBaseUrl() + "/getall";
//        ResponseEntity<Supplier[]> response = restTemplate.getForEntity(url, Supplier[].class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertTrue(response.getBody().length > 0);
//        System.out.println("All Suppliers: " + response.getBody().length);
//    }
//}

