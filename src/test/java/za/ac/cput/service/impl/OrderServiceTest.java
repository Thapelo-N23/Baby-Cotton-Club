package za.ac.cput.service.impl;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderLine;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.OrderLineFactory;
import za.ac.cput.service.ICustomerService;
import za.ac.cput.service.IOrderService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderServiceTest {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ICustomerService customerService;

    private static  List<OrderLine> orderLines = Arrays.asList(
            OrderLineFactory.createOrderLine(2, 50.00),
            OrderLineFactory.createOrderLine(1, 150.00)
    );

    private static Customer customer;
    private static Order order;

    @Test
    @org.junit.jupiter.api.Order(1)
    void create() {

        customer = customerService.create(
                CustomerFactory.createCustomer(
                        "John",
                        "Doe",
                        "mengezi@gmail.com",
                        "0781234567",
                        Arrays.asList(),
                        Arrays.asList(),
                        Arrays.asList()

                )
        );
        assertNotNull(customer.getCustomerId(), "Customer ID should not be null after save");

        // Create the order
        order = OrderFactory.createOrder(
                "20250518",
                250.00,
                orderLines,
                customer
        );

        Order created = orderService.create(order);
        assertNotNull(created, "Created order should not be null");
        assertNotNull(created.getOrderId(), "Order ID should not be null");
        order = created;

        System.out.println("Created Order: " + created);
    }

@Transactional
    @Test
    @org.junit.jupiter.api.Order(2)
    void read() {
        assertNotNull(order, "Order must be created before reading.");

        Order readOrder = orderService.read(order.getOrderId());
        assertNotNull(readOrder, "Read order should not be null");
        assertEquals(order.getOrderId(), readOrder.getOrderId());
        System.out.println("Read Order: " + readOrder);
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void update() {
        assertNotNull(order, "Order must be created before updating.");

        Order updatedOrder = new Order.Builder()
                .copy(order)
                .setOrderDate(LocalDate.now().plusDays(1))
                .setTotalAmount(300.00)
                .build();

        Order updated = orderService.update(updatedOrder);
        assertNotNull(updated, "Updated order should not be null");
        assertEquals(300.00, updated.getTotalAmount());
        order = updated;

        System.out.println("Updated Order: " + updated);
    }
@Transactional
    @Test
    @org.junit.jupiter.api.Order(4)
    void getAll() {
        List<Order> orders = orderService.getAll();
        assertNotNull(orders, "Order list should not be null");
        assertFalse(orders.isEmpty(), "Order list should not be empty");
        System.out.println("All Orders: " + orders);
    }
}
