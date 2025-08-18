package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.domain.OrderLine;
import za.ac.cput.domain.Shipment;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.factory.CustomerOrderFactory;
import za.ac.cput.factory.OrderLineFactory;
import za.ac.cput.factory.ShipmentFactory;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerOrderControllerTest {

    private CustomerOrder customerOrder;
    private Customer customer;
    private List<OrderLine> orderLines;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/order";
    }

    @BeforeAll
    void setUp() {
        // Create minimal, valid orderLines
        orderLines = Arrays.asList(
                OrderLineFactory.createOrderLine(2, 50.0),
                OrderLineFactory.createOrderLine(1, 150.0)
        );

        // Create a clean customer
        customer = CustomerFactory.createCustomer(
                "John",
                "Doe",
                "john.doe@example.com",
                "0781234567",
                null,
                null,
                null
        );

        // Create shipment
        Shipment shipment = ShipmentFactory.createShipment("DHL", "OUT OF STOCK", 23, null);

        // Create customerOrder with string date and orderLines
        customerOrder = CustomerOrderFactory.createCustomerOrder(
                "20250518",
                250.0,
                orderLines,
                customer,
                shipment
        );

        // POST to create order
        ResponseEntity<CustomerOrder> response = restTemplate.postForEntity(
                getBaseUrl() + "/create",
                customerOrder,
                CustomerOrder.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        customerOrder = response.getBody();
        assertNotNull(customerOrder.getOrderId());
    }

    @Test
    void a_create() {
        assertNotNull(customerOrder);
        System.out.println("Created Order: " + customerOrder);
    }

    @Test
    void b_read() {
        String url = getBaseUrl() + "/read/" + customerOrder.getOrderId();
        ResponseEntity<CustomerOrder> response = restTemplate.getForEntity(url, CustomerOrder.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(customerOrder.getOrderId(), response.getBody().getOrderId());

        System.out.println("Read Order: " + response.getBody());
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
        System.out.println("Updated Order: " + customerOrder);
    }

    @Test
    void d_getall() {
        ResponseEntity<CustomerOrder[]> response = restTemplate.getForEntity(
                getBaseUrl() + "/getall",
                CustomerOrder[].class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);

        System.out.println("All Orders count: " + response.getBody().length);
    }
}
