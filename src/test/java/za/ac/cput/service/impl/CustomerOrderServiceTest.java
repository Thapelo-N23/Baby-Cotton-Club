package za.ac.cput.service.impl;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.domain.OrderLine;
import za.ac.cput.domain.Shipment;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.factory.CustomerOrderFactory;
import za.ac.cput.factory.OrderLineFactory;
import za.ac.cput.factory.ShipmentFactory;
import za.ac.cput.service.ICustomerOrderService;
import za.ac.cput.service.ICustomerService;
import za.ac.cput.service.ShipmentService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.MethodName.class)
class CustomerOrderServiceTest {

    @Autowired
    private ICustomerOrderService customerOrderService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ShipmentService shipmentService;

    private Customer customer;
    private CustomerOrder customerOrder;

    @BeforeEach
    void setUp() {
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


        List<OrderLine> orderLines = Arrays.asList(
                OrderLineFactory.createOrderLine(2, 50.00),
                OrderLineFactory.createOrderLine(1, 150.00)
        );

        Shipment shipment = ShipmentFactory.createShipment("DHL", "OUT OF STOCK", 23,null);
        shipment = shipmentService.create(shipment);

        customerOrder = customerOrderService.create(
                CustomerOrderFactory.createCustomerOrder(
                        "20250722",
                        250.00,
                        orderLines,
                        customer,
                        shipment
                )
        );
    }

    @Test
    @Order(1)
    void create() {
        assertNotNull(customerOrder, "Created order should not be null");
        assertNotNull(customerOrder.getOrderId(), "Order ID should not be null");

        System.out.println("Created Order: " + customerOrder);
    }
    @Test
    @Order(2)
    void read() {
        CustomerOrder read = customerOrderService.read(customerOrder.getOrderId());
        assertNotNull(read);
        System.out.println("Read Order: " + read);
    }

    @Test
    @Order(3)
    void update() {
        CustomerOrder updatedOrder = new CustomerOrder.Builder()
                .build();

        CustomerOrder updated = customerOrderService.update(updatedOrder);
        System.out.println("Updated Order: " + updated);
    }

    @Test
    @Order(4)
    void getAll() {
        assertNotNull(customerOrderService.getAll());
        System.out.println("All Customers: " + customerOrderService.getAll());

    }
}
