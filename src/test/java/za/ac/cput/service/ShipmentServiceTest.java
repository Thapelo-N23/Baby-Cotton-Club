
package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.domain.Shipment;
import za.ac.cput.factory.CustomerOrderFactory;
import za.ac.cput.factory.ShipmentFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class ShipmentServiceTest {

 @Autowired
 private ShipmentService service;
private CustomerOrder customerOrder;
 private static Shipment shipment;
 private List<za.ac.cput.domain.CustomerOrder> CustomerOrder;


 @Test
 @Order(1)
 void a_create() {


  customerOrder = CustomerOrderFactory.createCustomerOrder(
          "20250518",
          250.00,
          null,
          null,
          shipment
  );

shipment = ShipmentFactory.createShipment(
"DHL", "on-track", 90.0, CustomerOrder
);
  Shipment created = service.create(shipment);
  assertNotNull(created);
  shipment = created;
  System.out.println("Created: " + created);
 }

 @Test
 @Order(2)
 void b_read() {
  Shipment read = service.read(shipment.getShipmentId());
  assertNotNull(read);
  System.out.println("Read: " + read);
 }

 @Test
 @Order(3)
 void c_update() {
  Shipment updatedShipment = new Shipment.Builder()
          .copy(shipment)
          .setShipmentStatus("Delivered")
          .build();

  Shipment updated = service.update(updatedShipment);
  assertNotNull(updated);
  shipment = updated; // store updated entity
  System.out.println("Updated: " + updated);
 }

 @Test
 @Order(4)
 void d_getAll() {
  System.out.println("All Shipments: " + service.getAll());
 }
}
