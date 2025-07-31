package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderLine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static za.ac.cput.factory.CustomerFactoryTest.*;

class OrderFactoryTest {

    @Test
    void createOrder() {
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(OrderLineFactory.createOrderLine(2, 50.00));
        orderLines.add(OrderLineFactory.createOrderLine(1, 150.00));

        Customer customer = CustomerFactory.createCustomer(
                "John",
                "Doe",
                "john.doe@example.com",
                "0712345678",
                null,
                null,
                null
        );

        Order order = OrderFactory.createOrder(
                "20250518",
                250.00,
                orderLines,
                customer
        );

        assertNotNull(order, "Order should not be null");
        assertEquals(LocalDate.of(2025, 5, 18), order.getOrderDate());
        assertEquals(250.00, order.getTotalAmount(), 0.001);
        assertEquals(orderLines, order.getOrderLine());
        assertEquals(customer, order.getCustomer());

        System.out.println(order);
    }

    @Test
    void createOrderWithCustomer() {
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(OrderLineFactory.createOrderLine(1, 99.99));

        Customer customer = CustomerFactory.createCustomer(
                "John",
                "Doe",
                "mengezi@gmail.com",
                "0781234567",
                Arrays.asList(address1),
                Arrays.asList(order1),
                Collections.emptyList()
        );

        Order order = OrderFactory.createOrder(
                "20250729",
                99.99,
                orderLines,
                customer
        );

        assertNotNull(order, "Order should not be null when customer is valid");
        assertEquals(LocalDate.of(2025, 7, 29), order.getOrderDate());
        assertEquals(99.99, order.getTotalAmount(), 0.001);
        assertEquals(orderLines, order.getOrderLine());
        assertEquals(customer, order.getCustomer());

        System.out.println(order);
    }


}
