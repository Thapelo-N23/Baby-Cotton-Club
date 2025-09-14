 package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import za.ac.cput.domain.Payment;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PaymentControllerTest {

    private Payment payment;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/payment";
    }

    @BeforeAll
    void setUp() {
        Payment requestPayment = new Payment.Builder()
                .setPaymentDate(LocalDate.parse("2025-08-21"))
                .setPaymentMethod("Credit Card")
                .build();

        String url = getBaseUrl() + "/create";
        ResponseEntity<Payment> response = restTemplate.postForEntity(url, requestPayment, Payment.class);

        assertEquals(HttpStatus.OK, response.getStatusCode(), "Failed to create a payment");
        payment = response.getBody();
        assertNotNull(payment);
        System.out.println("Test server running on: http://localhost:" + port);
    }

    @Test
    void a_createPayment() {
        assertNotNull(payment);
        assertTrue(payment.getPaymentId() > 0);
        System.out.println("Payment created: " + payment);
    }

    @Test
    void b_readPayment() {
        String url = getBaseUrl() + "/read/" + payment.getPaymentId();
        ResponseEntity<Payment> response = restTemplate.getForEntity(url, Payment.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(payment.getPaymentId(), response.getBody().getPaymentId());
        System.out.println("Read Payment: " + response.getBody());
    }

    @Test
    void c_updatePayment() {
        Payment updated = new Payment.Builder()
                .copy(payment)
                .setPaymentMethod("Updated EFT")
                .build();

        HttpEntity<Payment> request = new HttpEntity<>(updated);
        String url = getBaseUrl() + "/update";
        ResponseEntity<Payment> response = restTemplate.exchange(url, HttpMethod.PUT, request, Payment.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Updated EFT", response.getBody().getPaymentMethod());

        payment = response.getBody();
        System.out.println("Updated Payment: " + payment);
    }

    @Test
    void d_getAllPayments() {
        String url = getBaseUrl() + "/getall";
        ResponseEntity<Payment[]> response = restTemplate.getForEntity(url, Payment[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        System.out.println("All Payments: " + response.getBody().length);
    }
}
