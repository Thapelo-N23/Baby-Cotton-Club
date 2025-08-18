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
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.domain.Shipment;
import za.ac.cput.factory.ShipmentFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShipmentControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    private final String Base_URL = "http://localhost:8080/shipment";
    private static Shipment shipment;

    @BeforeAll
    public static void setUp() {
        shipment = ShipmentFactory.createShipment("POSTNET", "dispatched", 90, null,null);

    }




    @Test
    @Order(1)
    void createShipment() {
        String createShipmentUrl = Base_URL + "/create";
        ResponseEntity<Shipment> postResponse = testRestTemplate.postForEntity(createShipmentUrl, shipment, Shipment.class);
        shipment = postResponse.getBody();
        assertNotNull(shipment);
        System.out.println("Shipment created: " + shipment);
    }

    @Test
    @Order(2)
    void readShipment() {
        assertNotNull(shipment); // ensure it was created
        String readUrl = Base_URL + "/read/" + shipment.getShipmentId();
        ResponseEntity<Shipment> response = testRestTemplate.getForEntity(readUrl, Shipment.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println("Reading shipment with ID: " + shipment.getShipmentId());
        assertNotNull(shipment);
        assertNotNull(shipment.getShipmentId());

    }



    @Test
    @Order(3)
    void updateShipment() {
        assertNotNull(shipment); // prevent NPE
        String updateShipmentUrl = Base_URL + "/update";

        Shipment shipmentUpdate = new Shipment.Builder()
                .copy(shipment)
                .setShipmentStatus("delivered") // change some data
                .build();

        HttpEntity<Shipment> requestEntity = new HttpEntity<>(shipmentUpdate);
        ResponseEntity<Shipment> response = testRestTemplate.exchange(updateShipmentUrl, HttpMethod.PUT, requestEntity, Shipment.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Shipment updatedShipment = response.getBody();
        assertNotNull(updatedShipment);
        System.out.println("Shipment updated: " + updatedShipment);
    }


    @Test
    @Order(4)
    void getall() {
        System.out.println("Reading shipment with ID: " + shipment.getShipmentId());
        String allShipmentsUrl = Base_URL + "/getall";
        System.out.println("Getting all " + allShipmentsUrl);
        HttpEntity<String> requestEntity = new HttpEntity<>(null);
        ResponseEntity<String> response = testRestTemplate.exchange(allShipmentsUrl, HttpMethod.GET, requestEntity, String.class);
        System.out.println("All shipments" + response);
    }
}