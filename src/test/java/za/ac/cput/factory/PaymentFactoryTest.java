/*
PaymentFactoryTest POJO Class
Author: Phindile Lisa Ngozi
Student Number: 230640893
Date: 18 May 2025
 */
package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.Payment;

import static org.junit.jupiter.api.Assertions.*;

class PaymentFactoryTest {

    private static final Order dummyOrder = new Order.Builder()
            .setOrderId(1001)
            .setOrderDate(java.time.LocalDate.now())
            .build();

    private static final Payment payment = PaymentFactory.createPayment(
            "2025-07-22",
            "Credit Card",
            1,
            dummyOrder
    );

    @Test
    void createPayment() {
        assertNotNull(payment);
        System.out.println(payment);
    }

    @Test
    void createPaymentWithInvalidDate() {
        Payment invalid = PaymentFactory.createPayment(
                "invalid-date",
                "EFT",
                2,
                dummyOrder
        );
        assertNull(invalid, "Payment with invalid date should be null");
        System.out.println(invalid);
    }

    @Test
    void createPaymentWithNullOrder() {
        Payment invalid = PaymentFactory.createPayment(
                "2025-07-22",
                "Cash",
                3,
                null
        );
        assertNull(invalid, "Payment with null order should be null");
        System.out.println(invalid);
    }

    @Test
    void createPaymentWithEmptyMethod() {
        Payment invalid = PaymentFactory.createPayment(
                "2025-07-22",
                "   ",
                4,
                dummyOrder
        );
        assertNull(invalid, "Payment with empty method should be null");
        System.out.println(invalid);
    }
}