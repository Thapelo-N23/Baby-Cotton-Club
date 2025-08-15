package za.ac.cput.service.impl;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.domain.OrderLine;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.factory.CustomerOrderFactory;
import za.ac.cput.factory.OrderLineFactory;
import za.ac.cput.service.ICustomerOrderService;
import za.ac.cput.service.ICustomerService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
@Transactional
class CustomerOrderServiceTest {

    @Autowired
    private ICustomerOrderService orderService;

    @Autowired
    private ICustomerService customerService;

    private static Customer customer;
    private static CustomerOrder customerOrder;

    @Test
    void test1_create() {
        // Create customer
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

        // Sample order lines
        List<OrderLine> orderLines = Arrays.asList(
                OrderLineFactory.createOrderLine(2, 50.00),
                OrderLineFactory.createOrderLine(1, 150.00)
        );

        // Create order
        String orderDateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        customerOrder = orderService.create(
                CustomerOrderFactory.createCustomerOrder(
                        orderDateStr,
                        250.00,
                        orderLines,
                        customer
                )
        );

        assertNotNull(customerOrder, "Created order should not be null");
        assertNotNull(customerOrder.getOrderId(), "Order ID should not be null");

        System.out.println("Created Order: " + customerOrder);
    }

    @Test
    void test2_read() {
        List<CustomerOrder> allOrders = orderService.getAll();
        assertFalse(allOrders.isEmpty(), "No orders found in DB");

        CustomerOrder existing = allOrders.get(0);
        CustomerOrder read = orderService.read(existing.getOrderId());

        assertNotNull(read, "Read order should not be null");
        assertEquals(existing.getOrderId(), read.getOrderId());

        System.out.println("Read Order: " + read);
    }

    @Test
    void test3_update() {
        List<CustomerOrder> allOrders = orderService.getAll();
        assertFalse(allOrders.isEmpty(), "No orders found to update");

        CustomerOrder existingOrder = allOrders.get(0);

        CustomerOrder updatedOrder = new CustomerOrder.Builder()
                .copy(existingOrder)
                .setOrderDate(LocalDate.now().plusDays(1))
                .setTotalAmount(300.00)
                .build();

        CustomerOrder updated = orderService.update(updatedOrder);

        assertNotNull(updated, "Updated order should not be null");
        assertEquals(300.00, updated.getTotalAmount());

        System.out.println("Updated Order: " + updated);
    }

    @Test
    void test4_getAll() {
        List<CustomerOrder> orders = orderService.getAll();
        assertNotNull(orders, "Order list should not be null");
        assertFalse(orders.isEmpty(), "Order list should not be empty");

        System.out.println("All Orders: " + orders);
    }
}
