
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PaymentServiceTest {

    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ICustomerOrderService orderService;

    private static Customer customer;
    private static CustomerOrder customerOrder;
    private static Payment payment;

    @Test
    @Order(1)
    void create() {

        customer = customerService.create(
                CustomerFactory.createCustomer(
                        "Phindile", "Ngozi",
                        "phindile@gmail.com",
                        "0821234567",
                        List.of(),
                        List.of(),
                        List.of()
                )
        );
        assertNotNull(customer.getCustomerId(), "Customer ID should not be null");

        List<OrderLine> orderLines = List.of(
                OrderLineFactory.createOrderLine(2, 50.00)
        );
        Shipment shipment = ShipmentFactory.createShipment("DHL", "OUT OF STOCK", 23);
        customerOrder = orderService.create(
                CustomerOrderFactory.createCustomerOrder(
                        "20250803",
                        100.00,
                        orderLines,
                        customer,
                        shipment
                )
        );
        assertNotNull(customerOrder.getOrderId(), "Order ID should not be null");

        payment = paymentService.create(
                PaymentFactory.createPayment(
                        "20250803",
                        "Card",
                        customerOrder
                )
        );
        assertNotNull(payment.getPaymentId(), "Payment ID should not be null");

        System.out.println("Created Payment: " + payment);
    }

    @Transactional
    @Test
    @Order(2)
    void read() {
        assertNotNull(payment, "Payment must be created before reading");
        Payment readPayment = paymentService.read(payment.getPaymentId());
        assertNotNull(readPayment, "Read payment should not be null");
        assertEquals(payment.getPaymentId(), readPayment.getPaymentId());
        System.out.println("Read Payment: " + readPayment);
    }

    @Test
    @Order(3)
    void update() {
        assertNotNull(payment, "Payment must be created before updating");
        Payment updatedPayment = new Payment.Builder()
                .copy(payment)
                .setPaymentMethod("EFT")
                .build();

        Payment result = paymentService.update(updatedPayment);
        assertNotNull(result, "Updated payment should not be null");
        assertEquals("EFT", result.getPaymentMethod());
        payment = result;

        System.out.println("Updated Payment: " + result);
    }

    @Transactional
    @Test
    @Order(4)
    void getAll() {
        List<Payment> allPayments = paymentService.getAll();
        assertNotNull(allPayments, "Payment list should not be null");
        assertFalse(allPayments.isEmpty(), "Payment list should not be empty");
        System.out.println("All Payments: " + allPayments);
    }
}
