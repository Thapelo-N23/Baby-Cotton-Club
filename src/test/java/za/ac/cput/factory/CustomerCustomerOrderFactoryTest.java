package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.domain.OrderLine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static za.ac.cput.factory.CustomerFactoryTest.*;

class CustomerCustomerOrderFactoryTest {

    @Test
    void createCustomerOrder() {
        List<OrderLine> orderLines = new ArrayList<>();

        OrderLine orderLine = OrderLineFactory.createOrderLine(2, 50.00);

        Customer customer = CustomerFactory.createCustomer(
                "John",
                "Doe",
                "mengezi@gmail.com",
                "0781234567",
                Arrays.asList(address1),
                Arrays.asList(customerOrder1),
                Arrays.asList(review)

        );

        CustomerOrder customerOrder = CustomerOrderFactory.createCustomerOrder(
                "20250518",
                250.00,
                orderLines,
                customer
        );

        assertNotNull(customerOrder, "Order should not be null");
        assertEquals(LocalDate.of(2025, 5, 18), customerOrder.getOrderDate());
        assertEquals(250.00, customerOrder.getTotalAmount(), 0.001);
        assertEquals(orderLines, customerOrder.getOrderLines());
        assertEquals(customer, customerOrder.getCustomer());

        System.out.println(customerOrder);
    }

    @Test
    void createCustomerOrderWithCustomer() {
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(OrderLineFactory.createOrderLine(1, 99.99));

        Customer customer = CustomerFactory.createCustomer(
                "John",
                "Doe",
                "mengezi@gmail.com",
                "0781234567",
                Arrays.asList(address1),
                Arrays.asList(customerOrder1),
                Collections.emptyList()
        );

        CustomerOrder customerOrder = CustomerOrderFactory.createCustomerOrder(
                "20250729",
                99.99,
                orderLines,
                customer
        );

        assertNotNull(customerOrder, "Order should not be null when customer is valid");
        assertEquals(LocalDate.of(2025, 7, 29), customerOrder.getOrderDate());
        assertEquals(99.99, customerOrder.getTotalAmount(), 0.001);
        assertEquals(orderLines, customerOrder.getOrderLines());
        assertEquals(customer, customerOrder.getCustomer());

        System.out.println(customerOrder);
    }


}
