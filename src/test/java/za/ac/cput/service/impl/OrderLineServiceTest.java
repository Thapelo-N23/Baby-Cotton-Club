package za.ac.cput.service.impl;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.OrderLine;
import za.ac.cput.factory.OrderLineFactory;
import za.ac.cput.service.IOrderLineService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderLineServiceTest {

    @Autowired
    private IOrderLineService service;

    private static OrderLine orderLine;

    @Test
    @Order(1)
    void create() {
        orderLine = OrderLineFactory.createOrderLine(123, 123.0);
        OrderLine created = service.create(orderLine);
        assertNotNull(created);
        orderLine = created;
        System.out.println("Created OrderLine: " + orderLine);
    }

    @Test
    @Order(2)
    void read() {
        OrderLine read = service.read(String.valueOf(orderLine.getOrderLineId()));
        assertNotNull(read);
        System.out.println("Read OrderLine: " + read);
    }

    @Test
    @Order(3)
    void update() {
        OrderLine updated = new OrderLine.Builder()
                .copy(orderLine)
                .setQuantity(200)
                .setUnitPrice(99.99)
                .build();
        OrderLine result = service.update(updated);
        assertNotNull(result);
        orderLine = result;
        System.out.println("Updated OrderLine: " + result);
    }

    @Test
    @Order(4)
    void getAll() {
        System.out.println("All OrderLines: " + service.getAll());
    }
}
