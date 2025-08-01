package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderLineFactoryTest {

    private static Product product;
    private static Discount discount;

    @BeforeAll
    public static void setup() {

    }

    @Test
    public void testCreateOrderLineWithMinimalAttributes() {
        OrderLine orderLine = OrderLineFactory.createOrderLine(123, 123.0);

        assertNotNull(orderLine);
        assertEquals(123, orderLine.getQuantity());
        assertEquals(123.0, orderLine.getUnitPrice());
        assertEquals(123 * 123.0, orderLine.getSubTotal());
        assertNull(orderLine.getDiscount());
        assertNull(orderLine.getProduct());
        assertNull(orderLine.getOrder());

        System.out.println(orderLine);
    }
    @Test
    public void testCreateOrderLineWithAllAttributes() {
//        Product product = new Product();
//        Discount discount = new Discount();
        Customer customer = new Customer();
        List<OrderLine> orderLines = new ArrayList<>();
        Order order = OrderFactory.createOrder("20250729", 200.0, orderLines, customer);
        product = ProductFactory.createProduct( "Nike", "Black", (short) 42, "available");
        discount = DiscountFactory.createDiscount(13, "Spring Sale", "Percentage", "20%", "2025-05-01", "2025-05-31");
        OrderLine orderLine = OrderLineFactory.createOrderLine(
                5,
                200.0,
                order,
                product,
                discount
        );

        assertNotNull(orderLine);
        assertEquals(5, orderLine.getQuantity());
        assertEquals(200.0, orderLine.getUnitPrice());
        assertEquals(1000.0, orderLine.getSubTotal());
        assertEquals(product, orderLine.getProduct());
        assertEquals(discount, orderLine.getDiscount());
        assertEquals(order, orderLine.getOrder());

        System.out.println(orderLine);
    }


    @Test
    public void testCreateOrderLineWithNullDiscount() {
        OrderLine orderLine = OrderLineFactory.createOrderLine(2, 50.00);

        assertNotNull(orderLine);
        assertEquals(2, orderLine.getQuantity());
        assertEquals(50.00, orderLine.getUnitPrice());
        assertEquals(100.00, orderLine.getSubTotal());
        assertNull(orderLine.getDiscount());

        System.out.println(orderLine);
    }
}
