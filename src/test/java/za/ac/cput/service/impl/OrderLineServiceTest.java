package za.ac.cput.service.impl;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.factory.OrderLineFactory;
import za.ac.cput.factory.ProductFactory;
import za.ac.cput.factory.DiscountFactory;
import za.ac.cput.service.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrderLineServiceTest {

    @Autowired
    private IOrderLineService service;

    @Autowired
    private ICustomerOrderService orderService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private IDiscountService discountService;

    private static OrderLine orderLine;
    private static Product product;
    private static CustomerOrder customerOrder;
    private static Customer customer;
    private static Discount discount;


    @Test
    void test1_create() {



        product = productService.create(
                ProductFactory.createProduct(
                        "Lancewood", "Yellow", (short) 50, "OUT OF STOCK",null
                )
        );


        discount = discountService.create(
                DiscountFactory.createDiscount(
                        "Winter Sale",
                        "Percentage",
                        "20%",
                        "20250801",
                        "20250831",
                        orderLine
                )
        );
        orderLine = service.create(
                OrderLineFactory.createOrderLine(
                        2,
                        50.00,
                        customerOrder,
                        product,
                        discount
                )
        );

        System.out.println("Created OrderLine: " + orderLine);
    }

    @Test
    void test2_read() {
        List<OrderLine> allOrderLines = service.getAll();
        assertFalse(allOrderLines.isEmpty(), "No order lines found in DB");

        OrderLine existing = allOrderLines.get(0);
        OrderLine read = service.read(existing.getOrderLineId());

        assertNotNull(read);
        assertEquals(existing.getOrderLineId(), read.getOrderLineId());

        System.out.println("Read OrderLine: " + read);
    }


    @Test
    void test3_update() {
        List<OrderLine> allOrderLines = service.getAll();
        assertFalse(allOrderLines.isEmpty(), "No order lines found to update");

        OrderLine existingOrderLine = allOrderLines.get(0);

        OrderLine updatedOrderLine = new OrderLine.Builder()
                .copy(existingOrderLine)
                .setQuantity(5)
                .build();

        OrderLine updated = service.update(updatedOrderLine);
        assertNotNull(updated, "Updated OrderLine should not be null");
        assertEquals(5, updated.getQuantity());

        System.out.println("Updated OrderLine: " + updated);
    }


    @Test
   void test4_getAll() {
        assertNotNull(service.getAll());
     System.out.println(service.getAll());
        }

}

