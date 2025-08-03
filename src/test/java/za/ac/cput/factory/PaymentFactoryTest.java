/*
PaymentFactoryTest POJO Class
Author: Phindile Lisa Ngozi
Student Number: 230640893
Date: 18 May 2025
*/

package za.ac.cput.factory;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
//import za.ac.cput.domain.Order;
import za.ac.cput.domain.Payment;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PaymentFactoryTest {

    private static final Order order = (Order) OrderFactory.createOrder(
            LocalDate.now().toString(),
            100.00,
            null,
            null
    );

    private static final Payment payment = PaymentFactory.createPayment(
            "2025-07-22",
            "Credit Card",
            null
    );

    @Test
    @Order(1)
    void createPayment() {
        assertNotNull(payment, "Payment should not be null");
        assertEquals(LocalDate.of(2025, 7, 22), payment.getPaymentDate());
        assertEquals("Credit Card", payment.getPaymentMethod());
        assertEquals(order, payment.getOrder(), "Payment should be linked to the correct Order");
        System.out.println(payment);
    }

    @Test
    @Order(2)
    void createPaymentWithInvalidDate() {
        Payment invalid = PaymentFactory.createPayment(
                "invalid-date",
                "EFT",
                null
        );
        assertNull(invalid, "Payment with invalid date should be null");
        System.out.println(invalid);
    }

    @Test
    @Order(3)
    void createPaymentWithNullMethod() {
        Payment invalid = PaymentFactory.createPayment(
                "2025-07-22",
                "",
                null

        );
        assertNull(invalid, "Payment with null or empty method should be null");
        System.out.println(invalid);
    }

    @Test
    @Order(4)
    void createPaymentWithNullOrder() {
        Payment invalid = PaymentFactory.createPayment(
                "2025-07-22",
                "Cash",
                null
        );
        assertNull(invalid, "Payment with null order should be null");
        System.out.println(invalid);
    }
}
