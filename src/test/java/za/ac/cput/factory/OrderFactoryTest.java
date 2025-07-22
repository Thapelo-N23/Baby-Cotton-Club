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
private static Order order = OrderFactory.createOrder(
            "20250518",
            250.00,
            2,
            20.00,
            null // Customer will be set later
    );

    @Test
    void createOrder() {
        assertNotNull(order);
        System.out.println(order);
    }

    @Test
    void createOrderWithInvalidDate() {
        Order invalidOrder = OrderFactory.createOrder(
                "invalid-date",
                250.00,
                2,
                20.00,
                null // Customer will be set later
        );
        assertNull(invalidOrder, "Order with invalid date should be null");
        System.out.println(invalidOrder);
    }

}
