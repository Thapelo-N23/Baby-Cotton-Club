/*
OrderLineFactoryTest Class
Author: Tsireledzo Netshilonwe
Student Number: 230666426
Date: 2025/05/10
*/
package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.OrderLine;

import static org.junit.jupiter.api.Assertions.*;

class OrderLineFactoryTest {


    private static OrderLine o2 = OrderLineFactory.createOrderLine("OL123", "O123", "P123", 20, 200.0, 2);

    @Test
    public void testCreateOrderLineWithAllAttributes() {
        assertNotNull(o2);
        System.out.println(o2.toString());
    }
    @Test
    void testCreateOrderLineWithEmptyProductId() {
        OrderLine orderLine = OrderLineFactory.createOrderLine("OL123", "O123", "", 20, 200.0, 2);
        assertNull(orderLine);
    }
    @Test //Its supposed to fail when the quantity is negative
    void testCreateOrderLineWithNegativeQuantity() {
        OrderLine orderLine = OrderLineFactory.createOrderLine("OL123", "O123", "P123", -5, 200.0, 2);
        assertNull(orderLine);
    }
}

