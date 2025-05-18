/*
PaymentFactoryTest POJO Class
Author: Phindile Lisa Ngozi
Student Number: 230640893
Date: 18 May 2025
 */
package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Payment;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PaymentFactoryTest {

    @Test
    void testCreatePaymentSuccess() {
        Payment payment = PaymentFactory.createPayment(
                "PAY001",
                "ORD1001",
                "20250518",
                "Credit Card",
                "Paid",
                250.00
        );

        assertNotNull(payment);
        assertEquals("PAY001", payment.getPaymentId());
        assertEquals("ORD1001", payment.getOrderId());
        assertEquals("Credit Card", payment.getPaymentMethod());
        assertEquals("Paid", payment.getPaymentStatus());
        assertEquals(250.00, payment.getAmount());
        System.out.println(payment);
    }

    @Test
    void testCreatePaymentWithEmptyPaymentId() {
        Payment payment = PaymentFactory.createPayment(
                "",
                "ORD1002",
                "20250518",
                "EFT",
                "Pending",
                180.00
        );
        assertNull(payment);
    }

    @Test
    void testCreatePaymentWithNegativeAmount() {
        Payment payment = PaymentFactory.createPayment(
                "PAY002",
                "ORD1003",
                "20250518",
                "Cash",
                "Failed",
                -50.00
        );
        assertNull(payment);
    }

  }

