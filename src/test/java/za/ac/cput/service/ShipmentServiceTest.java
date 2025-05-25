
/**
 * BabyCottonClub
 * Shipment.java
 * Author : Thapelo Ngwenya - 222260971
 * Date : 11 May 2025
 */


package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Shipment;
import za.ac.cput.factory.ShipmentFactory;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)

class ShipmentServiceTest {
    private ShipmentService shipmentService;
    private Shipment shipment = ShipmentFactory.createShipment("S1234", "DHL", "On route", Double.parseDouble("R " + 500));

@Test
    void a_create(){
    Shipment created = shipmentService.create(shipment);
    assertNotNull(created);
    System.out.println(created);
}
@Test
    void b_read(){
    Shipment read = shipmentService.read(shipment.getShipmentId());
    assertNotNull(read);
    System.out.println(read);
}

    @Test
    void c_update(){
    Shipment newShipment = new Shipment.Builder()
            .copy(shipment).build();
    Shipment updated = shipmentService.update(newShipment);
    assertNotNull(updated);
    System.out.println(updated);

    }
    @Test
    void e_getall(){
        System.out.println(shipmentService.getAll());
    }
}
//updated
