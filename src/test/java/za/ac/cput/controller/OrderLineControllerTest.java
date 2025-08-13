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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static za.ac.cput.factory.PaymentFactoryTest.orderLines;

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

        customer = CustomerFactory.createCustomer(
                "John",
                "Doe",
                "john.doe@example.com",
                "0781234567",
                null,
                null,
                null
        );


        customerOrder = CustomerOrderFactory.createCustomerOrder(
                "20250518",
                250.00,
                orderLines,
                customer
        );


        orderLine = OrderLineFactory.createOrderLine(
                3,
                100.0,
                customerOrder,
                null,
                null
        );


        ResponseEntity<OrderLine> response = restTemplate.postForEntity(getBaseUrl() + "/create", orderLine, OrderLine.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        orderLine = response.getBody();
        assertNotNull(orderLine);
        assertNotNull(orderLine.getOrderLineId());  // Assuming you have an ID field named orderLineId
    }

    @Test
    void a_create() {
        assertNotNull(orderLine);
        System.out.println("Created OrderLine: " + orderLine);
    }

    @Test
    void b_read() {
        String url = getBaseUrl() + "/read/" + orderLine.getOrderLineId();
        ResponseEntity<OrderLine> response = restTemplate.getForEntity(url, OrderLine.class);

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
        String url = getBaseUrl() + "/update";
        ResponseEntity<OrderLine> response = restTemplate.exchange(url, HttpMethod.PUT, request, OrderLine.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(5, response.getBody().getQuantity());
        assertEquals(120.0, response.getBody().getUnitPrice());

        orderLine = response.getBody();
        System.out.println("Updated OrderLine: " + orderLine);
    }

    @Test
    void d_getAll() {
        String url = getBaseUrl() + "/getAll";
        ResponseEntity<OrderLine[]> response = restTemplate.getForEntity(url, OrderLine[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);

        System.out.println("All OrderLines count: " + response.getBody().length);
    }
}
