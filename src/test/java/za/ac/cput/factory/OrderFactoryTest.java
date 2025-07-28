package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderLine;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderFactoryTest {

    @Test
    void createOrder() {
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(OrderLineFactory.createOrderLine(2, 50.00));
        orderLines.add(OrderLineFactory.createOrderLine(1, 150.00));


        Customer customer = new Customer();

        Order order = OrderFactory.createOrder(
                "2025-05-18",
                250.00,
                orderLines,
                customer
        );

        assertNotNull(order);
        System.out.println(order);
    }

    @Test
    void createOrderWithInvalidDate() {
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(OrderLineFactory.createOrderLine(1, 20.00));

        Customer customer = null;

        Order invalidOrder = OrderFactory.createOrder(
                "invalid-date",
                250.00,
                orderLines,
                customer
        );

        assertNull(invalidOrder, "Order with invalid date should be null");
        System.out.println(invalidOrder);
    }
}
