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
//import za.ac.cput.domain.Payment;
//import za.ac.cput.factory.OrderLineFactory;
//import za.ac.cput.factory.OrderFactory;
//import za.ac.cput.factory.PaymentFactory;
//import za.ac.cput.factory.CustomerFactory;
//
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestMethodOrder(MethodOrderer.MethodName.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class PaymentControllerTest {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    private Customer customer;
//    private Order order;
//    private Payment payment;
//
//    private String getBaseUrl() {
//        return "http://localhost:" + port + "/payment";
//    }
//
//    @BeforeAll
//    void setUp() {
//        List<OrderLine> orderLines = List.of(
//                OrderLineFactory.createOrderLine(2, 75.00)
//        );
//
//        customer = CustomerFactory.createCustomer(
//                "Phindile", "Ngozi", "phindile@gmail.com", "0821234567",
//                Collections.emptyList(), Collections.emptyList(), Collections.emptyList()
//        );
//
//        order = OrderFactory.createOrder("2025-08-03", 150.00, orderLines, customer);
//
//        payment = PaymentFactory.createPayment("2025-08-03", "Credit Card", order);
//
//        String url = getBaseUrl() + "/create";
//        ResponseEntity<Payment> response = restTemplate.postForEntity(url, payment, Payment.class);
//        assertEquals(HttpStatus.OK, response.getStatusCode(), "Failed to create payment in setup");
//        payment = response.getBody();
//        assertNotNull(payment);
//    }
//
//    @Test
//    void a_create() {
//        assertNotNull(payment);
//        assertNotEquals(0, payment.getPaymentId());
//        System.out.println("Created Payment: " + payment);
//    }
//
//    @Test
//    void b_read() {
//        String url = getBaseUrl() + "/read/" + payment.getPaymentId();
//        ResponseEntity<Payment> response = restTemplate.getForEntity(url, Payment.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(payment.getPaymentId(), response.getBody().getPaymentId());
//        System.out.println("Read Payment: " + response.getBody());
//    }
//
//    @Test
//    void c_update() {
//        Payment updated = new Payment.Builder()
//                .copy(payment)
//                .setPaymentMethod("Updated EFT")
//                .build();
//
//        HttpEntity<Payment> request = new HttpEntity<>(updated);
//        String url = getBaseUrl() + "/update";
//        ResponseEntity<Payment> response = restTemplate.exchange(url, HttpMethod.PUT, request, Payment.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals("Updated EFT", response.getBody().getPaymentMethod());
//
//        payment = response.getBody();
//        System.out.println("Updated Payment: " + payment);
//    }
//
//    @Test
//    void d_getAll() {
//        String url = getBaseUrl() + "/getall";
//        ResponseEntity<Payment[]> response = restTemplate.getForEntity(url, Payment[].class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertTrue(response.getBody().length > 0);
//        System.out.println("All Payments: " + response.getBody().length);
//    }
//}

