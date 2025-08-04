//package za.ac.cput.service.impl;
//
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.Order;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import za.ac.cput.domain.*;
//import za.ac.cput.factory.*;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class PaymentServiceTest {
//
//        @Autowired
//        private PaymentService service;
//
//        private static Customer customer;
//        private static Order order;
//        private static Payment payment;
//
//        @Test
//        @Order(1)
//        void create() {
//            List<OrderLine> orderLines = List.of(OrderLineFactory.createOrderLine(2, 50.00));
//
//            customer = CustomerFactory.createCustomer(
//                    "Phindile", "Ngozi", "phindile@gmail.com", "0821234567",
//                    Collections.emptyList(), Collections.emptyList(), Collections.emptyList()
//            );
//
//            order = (Order) OrderFactory.createOrder("2025-08-03", 100.00, orderLines, customer);
//
//            payment = PaymentFactory.createPayment("2025-08-03", "Card", null);
//            assertNotNull(payment);
//
//            Payment created = service.create(payment);
//            assertNotNull(created);
//            payment = created;
//
//            System.out.println("Created Payment: " + created);
//        }
//
//        @Test
//        @Order(2)
//        void read() {
//            Payment read = service.read(payment.getPaymentId());
//            assertNotNull(read);
//            System.out.println("Read Payment: " + read);
//        }
//
//        @Test
//        @Order(3)
//        void update() {
//            Payment updated = new Payment.Builder()
//                    .copy(payment)
//                    .setPaymentMethod("Updated EFT")
//                    .build();
//
//            Payment result = service.update(updated);
//            assertNotNull(result);
//            payment = result;
//
//            System.out.println("Updated Payment: " + result);
//        }
//
//        @Test
//        @Order(4)
//        void getAll() {
//            List<Payment> allPayments = service.getAll();
//            assertNotNull(allPayments);
//            assertFalse(allPayments.isEmpty());
//            System.out.println("All Payments: " + allPayments);
//        }
//    }
