package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderLine;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Discount;

import static org.junit.jupiter.api.Assertions.*;

class OrderLineFactoryTest {

    private static Order dummyOrder;
    private static Product dummyProduct;
    private static Discount dummyDiscount;
    private static OrderLine orderLine;

    @BeforeAll
    public static void setup() {
        // Create simple dummy instances
        dummyOrder = new Order.Builder().setOrderId(1L).build();
        dummyProduct = new Product.Builder().setProductId(1L).build();
        dummyDiscount = null;  // Or create a Discount object if you want to test discount logic

        orderLine = OrderLineFactory.createOrderLine(dummyOrder, dummyProduct, 123, 123.0, dummyDiscount);
    }

    @Test
    public void testCreateOrderLineWithAllAttributes() {
        assertNotNull(orderLine);
        assertEquals(123, orderLine.getQuantity());
        assertEquals(123.0, orderLine.getUnitPrice());
        assertEquals(123 * 123.0, orderLine.getSubTotal());

        assertEquals(dummyOrder.getOrderId(), orderLine.getOrder().getOrderId());
        assertEquals(dummyProduct.getProductId(), orderLine.getProduct().getProductId());
        assertNull(orderLine.getDiscount());

        System.out.println(orderLine);
    }
}
