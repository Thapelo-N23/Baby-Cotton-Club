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


    private static OrderLine o2 = OrderLineFactory.createOrderLine(123, 123.0);

    @Test
    public void testCreateOrderLineWithAllAttributes() {
        assertNotNull(o2);
        System.out.println(o2.toString());
    }

}

