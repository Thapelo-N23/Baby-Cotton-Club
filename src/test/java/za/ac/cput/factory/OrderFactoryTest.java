/*
OrderFactoryTest Class
Author: Tsireledzo Netshilonwe
Student Number: 230666426
Date: 2025/05/10
*/
package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Order;

import static org.junit.jupiter.api.Assertions.*;
class OrderFactoryTest {

    private OrderFactory order;
    private static Order order1 = OrderFactory.createOrder("20250707", 23322, 20, 200.0);

    @Test
    public void testCreateOrderWithAllAttributes() {
        assertNotNull(order1);
        System.out.println(order1.toString());
    }

}
