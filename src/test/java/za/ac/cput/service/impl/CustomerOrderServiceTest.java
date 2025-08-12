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
import za.ac.cput.service.ICustomerService;
import za.ac.cput.service.ICustomerOrderService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerOrderServiceTest {

    @Autowired
    private ICustomerOrderService orderService;

    @Autowired
    private ICustomerService customerService;

    private static  List<OrderLine> orderLines = Arrays.asList(
            OrderLineFactory.createOrderLine(2, 50.00),
            OrderLineFactory.createOrderLine(1, 150.00)
    );

    private static Customer customer;
    private static CustomerOrder customerOrder;

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
        customerOrder = CustomerOrderFactory.createCustomerOrder(
                "20250518",
                250.00,
                orderLines,
                customer
        );

        CustomerOrder created = orderService.create(customerOrder);
        assertNotNull(created, "Created order should not be null");
        assertNotNull(created.getOrderId(), "Order ID should not be null");
        customerOrder = created;

        System.out.println("Created Order: " + created);
    }

@Transactional
    @Test
    @org.junit.jupiter.api.Order(2)
    void read() {
        assertNotNull(customerOrder, "Order must be created before reading.");

        CustomerOrder readCustomerOrder = orderService.read(customerOrder.getOrderId());
        assertNotNull(readCustomerOrder, "Read order should not be null");
        assertEquals(customerOrder.getOrderId(), readCustomerOrder.getOrderId());
        System.out.println("Read Order: " + readCustomerOrder);
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void update() {
        assertNotNull(customerOrder, "Order must be created before updating.");

        CustomerOrder updatedCustomerOrder = new CustomerOrder.Builder()
                .copy(customerOrder)
                .setOrderDate(LocalDate.now().plusDays(1))
                .setTotalAmount(300.00)
                .build();

        CustomerOrder updated = orderService.update(updatedCustomerOrder);
        assertNotNull(updated, "Updated order should not be null");
        assertEquals(300.00, updated.getTotalAmount());
        customerOrder = updated;

        System.out.println("Updated Order: " + updated);
    }
@Transactional
    @Test
    @org.junit.jupiter.api.Order(4)
    void getAll() {
        List<CustomerOrder> customerOrders = orderService.getAll();
        assertNotNull(customerOrders, "Order list should not be null");
        assertFalse(customerOrders.isEmpty(), "Order list should not be empty");
        System.out.println("All Orders: " + customerOrders);
    }
}
