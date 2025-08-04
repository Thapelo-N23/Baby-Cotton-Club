//package za.ac.cput.controller;
//
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.*;
//import za.ac.cput.domain.Customer;
//import za.ac.cput.domain.Order;
//import za.ac.cput.domain.OrderLine;
//import za.ac.cput.factory.CustomerFactory;
//import za.ac.cput.factory.OrderFactory;
//import za.ac.cput.factory.OrderLineFactory;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static za.ac.cput.factory.CustomerFactoryTest.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestMethodOrder(MethodOrderer.MethodName.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class OrderControllerTest {
//
//    private Order order;
//    private Customer customer;
//    private List<OrderLine> orderLines;
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    private String getBaseUrl() {
//        return "http://localhost:" + port + "/api/order";
//    }
//
//    @BeforeAll
//    void setUp() {
//        orderLines = Arrays.asList(
//                OrderLineFactory.createOrderLine(2, 50.00),
//                OrderLineFactory.createOrderLine(1, 150.00)
//        );
//
//        customer = CustomerFactory.createCustomer(
//                "John",
//                "Doe",
//                "mengezi@gmail.com",
//                "0781234567",
//                Arrays.asList(address1),
//                Arrays.asList(order1),
//                Arrays.asList(review)
//        );
//
//        order = OrderFactory.createOrder(
//                LocalDate.now().toString(),
//                250.00,
//                orderLines,
//                customer
//        );
//
//        String url = getBaseUrl() + "/create";
//        ResponseEntity<Order> response = restTemplate.postForEntity(url, order, Order.class);
//        assertEquals(HttpStatus.OK, response.getStatusCode(), "Failed to create order");
//        order = response.getBody();
//        assertNotNull(order);
//        assertNotNull(order.getOrderId());
//    }
//
//    @Test
//    void a_create() {
//        assertNotNull(order);
//        System.out.println("Created Order: " + order);
//    }
//
//    @Test
//    void b_read() {
//        String url = getBaseUrl() + "/read/" + order.getOrderId();
//        ResponseEntity<Order> response = restTemplate.getForEntity(url, Order.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(order.getOrderId(), response.getBody().getOrderId());
//        System.out.println("Read Order: " + response.getBody());
//    }
//
//    @Test
//    void c_update() {
//        Order updatedOrder = new Order.Builder()
//                .copy(order)
//                .setTotalAmount(300.00)
//                .setOrderDate(LocalDate.now().plusDays(1))
//                .build();
//
//        HttpEntity<Order> request = new HttpEntity<>(updatedOrder);
//        String url = getBaseUrl() + "/update";
//        ResponseEntity<Order> response = restTemplate.exchange(url, HttpMethod.PUT, request, Order.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(300.00, response.getBody().getTotalAmount());
//
//        order = response.getBody();
//        System.out.println("Updated Order: " + order);
//    }
//
//    @Test
//    void d_findAll() {
//        String url = getBaseUrl() + "/findAll";
//        ResponseEntity<Order[]> response = restTemplate.getForEntity(url, Order[].class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertTrue(response.getBody().length > 0);
//        System.out.println("All Orders: " + response.getBody().length);
//    }
//}
