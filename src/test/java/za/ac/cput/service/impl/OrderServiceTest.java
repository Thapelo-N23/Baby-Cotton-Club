package za.ac.cput.service.impl;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Order;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.service.IOrderService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrderServiceTest {
//
//    private IOrderService service = OrderService.getOrderService();
//
//    private static Order order = OrderFactory.createOrder("20250707", 23322, 20, 200.0);
//
//
//
//
//    @Test
//    void a_create() {
//        Order newOrder = service.create(order);
//        assertNotNull(newOrder);
//        System.out.println("Created Order: " + newOrder);
//    }
//
//    @Test
//    void b_read() {
//        Order read = service.read(order.getOrderId());
//    }
//
//    @Test
//    void c_update() {
//    }
//
//    @Test
//    void e_getAll() {
//    }
}