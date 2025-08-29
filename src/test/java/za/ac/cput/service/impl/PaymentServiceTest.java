package za.ac.cput.service.impl;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;
import za.ac.cput.service.ICustomerOrderService;
import za.ac.cput.service.ICustomerService;
import za.ac.cput.service.IPaymentService;
import org.junit.jupiter.api.*;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PaymentServiceTest {

    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ICustomerOrderService customerOrderService;

    private Customer customer;
    private CustomerOrder customerOrder;
    private Payment payment;

    @BeforeEach
    void setUp() {
        customer = customerService.create(
            CustomerFactory.createCustomer(
                "John",
                "Doe",
                "mengezi@gmail.com",
                "0781234567",
                "securePassword123",
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                null
            )
        );

        List<OrderLine> orderLines = Arrays.asList(
            OrderLineFactory.createOrderLine(2, 50.00),
            OrderLineFactory.createOrderLine(1, 150.00)
        );

        customerOrder = customerOrderService.create(
            CustomerOrderFactory.createCustomerOrder(
                "20250722",
                250.00,
                orderLines,
                customer,
                null
            )
        );

        payment = paymentService.create(
            PaymentFactory.createPayment(
                "20250722",
                "Card",
                customerOrder
            )
        );
    }

    @Test
    @Order(1)
    void create() {
        assertNotNull(payment, "Created payment should not be null");
        assertNotNull(payment.getPaymentId(), "Payment ID should not be null");
        System.out.println("Created Payment: " + payment);
    }

    @Test
    @Order(2)
    void read() {
        Payment read = paymentService.read(payment.getPaymentId());
        assertNotNull(read);
        System.out.println("Read Payment: " + read);
    }

    @Test
    @Order(3)
    void update() {
        Payment updatedPayment = new Payment.Builder()
            .copy(payment)
            .setPaymentMethod("EFT")
            .build();
        Payment updated = paymentService.update(updatedPayment);
        assertNotNull(updated);
        assertEquals("EFT", updated.getPaymentMethod());
        System.out.println("Updated Payment: " + updated);
    }

    @Test
    @Order(4)
    void getAll() {
        assertNotNull(paymentService.getAll());
        System.out.println("All Payments: " + paymentService.getAll());
    }
}
