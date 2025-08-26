package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.domain.Review;
import za.ac.cput.factory.CustomerOrderFactory;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerOrderControllerTest {

    private CustomerOrder customerOrder;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/order";
    }

    @BeforeAll
    void setUp() {

        customerOrder = CustomerOrderFactory.createCustomerOrder(
                "20250518",
                250.0,
                null,
                null,
                null
        );


        ResponseEntity<CustomerOrder> response = restTemplate.postForEntity(
                getBaseUrl() + "/create",
                customerOrder,
                CustomerOrder.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        customerOrder = response.getBody();
        assertNotNull(customerOrder);
        assertNotNull(customerOrder.getOrderId());
        System.out.println("Created CustomerOrder (setup): " + customerOrder);
    }

    @Test
    void a_create() {
        assertNotNull(customerOrder);
        System.out.println("Created CustomerOrder: " + customerOrder);
    }

    @Test
    void b_read() {
        ResponseEntity<CustomerOrder> response = restTemplate.getForEntity(
                getBaseUrl() + "/read/" + customerOrder.getOrderId(),
                CustomerOrder.class
        );


        assertNotNull(response.getBody());
        assertEquals(customerOrder.getOrderId(), response.getBody().getOrderId());
        System.out.println("Read CustomerOrder: " + response.getBody());
    }

    @Test
    void c_update() {
        CustomerOrder updatedOrder = new CustomerOrder.Builder()
                .copy(customerOrder)
                .setTotalAmount(300.0)
                .build();

        HttpEntity<CustomerOrder> request = new HttpEntity<>(updatedOrder);
        ResponseEntity<CustomerOrder> response = restTemplate.exchange(
                getBaseUrl() + "/update",
                HttpMethod.PUT,
                request,
                CustomerOrder.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(300.0, response.getBody().getTotalAmount(), 0.01);

        customerOrder = response.getBody();
        System.out.println("Updated CustomerOrder: " + customerOrder);
    }

    @Test
    void d_getAll() {
        String url = getBaseUrl() + "/getall";
        ResponseEntity<CustomerOrder[]> response = restTemplate.getForEntity(url, CustomerOrder[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        System.out.println("All CustomerOrders count: " + response.getBody().length);


    }
}