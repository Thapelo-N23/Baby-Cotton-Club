/**
 * BabyCottonClub
 * Shipment.java
 * Author : Thapelo Ngwenya - 222260971
 * Date : 11 May 2025
 */

package za.ac.cput.factory;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Shipment;

import static org.junit.jupiter.api.Assertions.*;
class ShipmentFactoryTest {
    private static Shipment s1 = ShipmentFactory.createShipment("DHL", "OUT OF STOCK", 23);

    @Test

    public void testCreateEmployee() {
        assertNotNull(s1);
        System.out.println(s1);
    }

    @Test

    public void testCreateEmployeeWithAllAttributes() {
        assertNotNull(s1);
        System.out.println(s1);
    }

    @Test

    public void testCreateEmployeeThatFails(){
        assertNotNull(s1);
        System.out.println(s1);
    }

    @Test
    @Disabled
    public void testNotImplementedYet(){

    }

}