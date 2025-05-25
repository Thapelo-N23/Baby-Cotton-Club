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
    private static Order order1 = OrderFactory.createOrder("O123", "C123", "20231001", 200.0, 1);

    @Test
    public void testCreateOrderWithAllAttributes() {
        assertNotNull(order1);
        System.out.println(order1.toString());
    }
    @Test
    void testCreateOrderWithEmptyCustomerId() {
        Order invalidOrder = OrderFactory.createOrder("O123", "", "20231001", 200.0, 1);
        assertNull(invalidOrder);
    }
}
