/*
PaymentFactoryTest POJO Class
Author: Phindile Lisa Ngozi
Student Number: 230640893
Date: 18 May 2025
*/

package za.ac.cput.factory;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.domain.*;
import java.time.LocalDate;
import java.util.*;


import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaymentFactoryTest {

    public static final List<OrderLine> orderLines = new ArrayList<>(
            List.of(OrderLineFactory.createOrderLine(1, 100.00))
    );

    private static final Customer customer = CustomerFactory.createCustomer(
            "Olwethu",
            "Nene",
            "olwethunene43@gmail.com",
            "0821234567",
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
    );
    private static final Shipment shipment = ShipmentFactory.createShipment("DHL", "OUT OF STOCK", 23,null);
    private static final CustomerOrder CUSTOMER_ORDER = (CustomerOrder) CustomerOrderFactory.createCustomerOrder(
            "20250722",
            100.00,
            orderLines,
            customer,
            shipment
    );

    private static final Payment payment = PaymentFactory.createPayment(
            "20250722",
            "Credit Card",
            CUSTOMER_ORDER

    );

    @Test

    void createPayment() {
        assertNotNull(payment, "Payment should not be null");
        assertEquals(LocalDate.of(2025, 7, 22), payment.getPaymentDate());
        assertEquals("Credit Card", payment.getPaymentMethod());
        assertEquals(CUSTOMER_ORDER, payment.getOrder(), "Payment should be linked to the correct Order");
        System.out.println(payment);
    }


    @Test

    void createPaymentWithNullMethod() {
        Payment invalid = PaymentFactory.createPayment(
                "20250722",
                "",
                CUSTOMER_ORDER

        );
        assertNull(invalid, "Payment with null or empty method should be null");
        System.out.println(invalid);
    }

    @Test

    void createPaymentWithNullOrder() {
        Payment invalid = PaymentFactory.createPayment(
                "20250722",
                "Cash",
                null
        );
        assertNull(invalid, "Payment with null order should be null");
        System.out.println(invalid);
    }
}
