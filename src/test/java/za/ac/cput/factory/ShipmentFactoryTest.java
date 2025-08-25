/**
 * BabyCottonClub
 * Shipment.java
 * Author : Thapelo Ngwenya - 222260971
 * Date : 11 May 2025
 */

package za.ac.cput.factory;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.domain.OrderLine;
import za.ac.cput.domain.Shipment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class ShipmentFactoryTest {
    CustomerOrder customerOrder = CustomerOrderFactory.createCustomerOrder(
            "20250518",
            250.00,
            null,
            null,
            new Shipment()
    );

    OrderLine orderLine = OrderLineFactory.createOrderLine(
            5,
            200.0,
            null,
            null,
            null
    );

    Shipment s1 = ShipmentFactory.createShipment(
            "dhl",
            "enroute",
            679,
            List.of(customerOrder)
    );

    @Test
    @Order(1)
    public void testCreateShipment() {
        assertNotNull(s1);
        System.out.println(s1);
    }

    @Test
    @Order(2)
    public void testCreateShipmentWithAllAttributes() {
        assertNotNull(s1);
        System.out.println(s1);
    }

    @Test
    @Order(3)
    public void testCreateShipmentThatFails(){
        assertNotNull(s1);
        System.out.println(s1);
    }

    @Test
    @Order(4)
    @Disabled
    public void testNotImplementedYet() {}
}