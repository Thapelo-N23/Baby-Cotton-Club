package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderLineControllerTest {

    private OrderLine orderLine;
    private CustomerOrder customerOrder;
    private Customer customer;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/orderline";
    }

    @BeforeAll
    void setUp() {

        // Minimal customer
        customer = CustomerFactory.createCustomer(
                "John",
                "Doe",
                "john.doe@example.com",
                "0781234567",
                null,
                null,
                null
        );

        // Shipment
        Shipment shipment = ShipmentFactory.createShipment("DHL", "OUT OF STOCK", 23, null);

        // CustomerOrder with empty orderLines (they will be added below)
        customerOrder = CustomerOrderFactory.createCustomerOrder(
                "20250518",
                250.0,
                Arrays.asList(),
                customer,
                shipment
        );

        // OrderLine linked to customerOrder
        orderLine = OrderLineFactory.createOrderLine(
                3,
                100.0,
                customerOrder,
                null,
                null
        );

        // POST to create orderLine
        ResponseEntity<OrderLine> response = restTemplate.postForEntity(
                getBaseUrl() + "/create",
                orderLine,
                OrderLine.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        orderLine = response.getBody();
        assertNotNull(orderLine);
        assertNotNull(orderLine.getOrderLineId());
    }

    @Test
    void a_create() {
        assertNotNull(orderLine);
        System.out.println("Created OrderLine: " + orderLine);
    }

    @Test
    void b_read() {
        ResponseEntity<OrderLine> response = restTemplate.getForEntity(
                getBaseUrl() + "/read/" + orderLine.getOrderLineId(),
                OrderLine.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(orderLine.getOrderLineId(), response.getBody().getOrderLineId());
        System.out.println("Read OrderLine: " + response.getBody());
    }

    @Test
    void c_update() {
        OrderLine updatedOrderLine = new OrderLine.Builder()
                .copy(orderLine)
                .setQuantity(5)
                .setUnitPrice(120.0)
                .build();

        HttpEntity<OrderLine> request = new HttpEntity<>(updatedOrderLine);

        ResponseEntity<OrderLine> response = restTemplate.exchange(
                getBaseUrl() + "/update",
                HttpMethod.PUT,
                request,
                OrderLine.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(5, response.getBody().getQuantity());
        assertEquals(120.0, response.getBody().getUnitPrice());

        orderLine = response.getBody();
        System.out.println("Updated OrderLine: " + orderLine);
    }

    @Test
    void d_getAll() {
        ResponseEntity<OrderLine[]> response = restTemplate.getForEntity(
                getBaseUrl() + "/getall",
                OrderLine[].class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        System.out.println("All OrderLines count: " + response.getBody().length);
    }

}
