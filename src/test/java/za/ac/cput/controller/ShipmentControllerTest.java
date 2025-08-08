/**
 * BabyCottonClub
 * Shipment.java
 * Author : Thapelo Ngwenya - 222260971
 * Date : 11 May 2025
 */

package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Shipment;
import za.ac.cput.factory.ShipmentFactory;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShipmentControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    private final String Base_URL = "http://localhost:8080";
    private static Shipment shipment;

    @BeforeAll
    public static void setUp() {
        shipment = ShipmentFactory.createShipment( "POSTNET", "enroute", 70);
    }

    @Test
    @Order(1)
    void createShipment() {
        String createShipmentUrl = Base_URL + "/createShipment";
        ResponseEntity<Shipment>postResponse = testRestTemplate.postForEntity(createShipmentUrl, shipment, Shipment.class);
        Shipment createdShipment = postResponse.getBody();
        assert createdShipment != null;
        assertNotNull(shipment);
        System.out.println("Shipment created: " + createdShipment);
    }

    @Test
    @Order(2)
    void readShipment() {
        Long shipmentId = shipment.getShipmentId();
        String readShipmentUrl = Base_URL + "/readShipment/" + shipmentId;
        System.out.println("Reading shipment " + readShipmentUrl);
        ResponseEntity<Shipment> response = testRestTemplate.getForEntity(readShipmentUrl, Shipment.class);
        assertNotNull(response);
        System.out.println("Shipment read: " + response);
        assertNotNull(response.getBody());
        System.out.println("Shipment read: " + response.getBody());
    }

    @Test
    @Order(3)
    void updateShipment() {
        String updateShipmentUrl = Base_URL + "/updateShipment";
        System.out.println("Updating shipment " + updateShipmentUrl);
        Shipment shipmentUpdate = new Shipment.Builder().copy(shipment).build();
        HttpEntity<Shipment>requestEntity = new HttpEntity<>(shipmentUpdate);
        ResponseEntity<Shipment> response = testRestTemplate.exchange(updateShipmentUrl, HttpMethod.PUT, requestEntity, Shipment.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Shipment updatedShipment = response.getBody();
        assertNotNull(updatedShipment);
        System.out.println("Shipment updated: " + updatedShipment);
    }

    @Test
    @Order(4)
    void getall() {
        String allShipmentsUrl = Base_URL + "/getAllShipments";
        System.out.println("Getting all " + allShipmentsUrl);
        HttpEntity<String> requestEntity = new HttpEntity<>(null);
        ResponseEntity<String> response = testRestTemplate.exchange(allShipmentsUrl, HttpMethod.GET, requestEntity, String.class);
        System.out.println("All shipments" + response);
    }
}