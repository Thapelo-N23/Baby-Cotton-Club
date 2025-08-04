//package za.ac.cput.service.impl;
//
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import za.ac.cput.domain.Customer;
//
//import za.ac.cput.domain.OrderLine;
//import za.ac.cput.factory.CustomerFactory;
//import za.ac.cput.factory.OrderFactory;
//import za.ac.cput.factory.OrderLineFactory;
//import za.ac.cput.service.IOrderService;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static za.ac.cput.factory.CustomerFactoryTest.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class OrderServiceTest {
//
//    @Autowired
//    private IOrderService service;
//
//    private static List<OrderLine> orderLines = Arrays.asList(
//            OrderLineFactory.createOrderLine(2, 50.00),
//            OrderLineFactory.createOrderLine(1, 150.00)
//    );
//
//    private static Customer customer = CustomerFactory.createCustomer(
//            "John",
//            "Doe",
//            "mengezi@gmail.com",
//            "0781234567",
//            Arrays.asList(address1),
//            Arrays.asList(order1),
//            Arrays.asList(review)
//    );
//
//    private static Order order;
//
//    @Test
//    @Order(1)
//    void create() {
//        order = OrderFactory.createOrder(
//                LocalDate.now().toString(),
//                100.00,
//                orderLines,
//                customer
//        );
//
//        Order created = service.create(order);
//        assertNotNull(created);
//        order = created;
//        System.out.println("Created Order: " + order);
//    }
//
//    @Test
//    @Order(2)
//    void read() {
//        Order readOrder = service.read(String.valueOf(order.getOrderId()));
//        assertNotNull(readOrder);
//        assertEquals(order.getOrderId(), readOrder.getOrderId());
//        System.out.println("Read Order: " + readOrder);
//    }
//
//    @Test
//    @Order(3)
//    void update() {
//        Order updatedOrder = new Order.Builder()
//                .copy(order)
//                .setOrderDate(LocalDate.now().plusDays(1))
//                .setTotalAmount(120.00)
//                .build();
//
//        Order updated = service.update(updatedOrder);
//        assertNotNull(updated);
//        assertEquals(120.00, updated.getTotalAmount());
//        order = updated;
//        System.out.println("Updated Order: " + updated);
//    }
//
//    @Test
//    @Order(4)
//    void getAll() {
//        List<Order> orders = service.getAll();
//        assertNotNull(orders);
//        assertFalse(orders.isEmpty(), "Order list should not be empty");
//        System.out.println("All Orders: " + orders);
//    }
//}
