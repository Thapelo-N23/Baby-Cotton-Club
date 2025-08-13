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
import za.ac.cput.domain.Shipment;

import static org.junit.jupiter.api.Assertions.*;
class ShipmentFactoryTest {
    private static Shipment s1 = ShipmentFactory.createShipment( "DHL", "OUT OF STOCK", 23);

    @Test
    @Order(1)
    public void testCreateEmployee() {
        assertNotNull(s1);
        System.out.println(s1);
    }

    @Test
    @Order(2)
    public void testCreateEmployeeWithAllAttributes() {
        assertNotNull(s1);
        System.out.println(s1);
    }

    @Test
    @Order(3)
    public void testCreateEmployeeThatFails(){
        assertNotNull(s1);
        System.out.println(s1);
    }

    @Test
    @Order(4)
    @Disabled
    public void testNotImplementedYet(){

    }

}