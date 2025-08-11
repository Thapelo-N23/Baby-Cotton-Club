/*
 * BabyCottonClub
 * ShipmentController.java
 * Author : Thapelo Ngwenya - 222260971
 * Date : 11 May 2025
 */

package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Shipment;
import za.ac.cput.service.ShipmentService;

import java.util.List;

@RestController
@RequestMapping("/shipment")
public class ShipmentController {

    private final ShipmentService shipmentService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Shipment> create(@RequestBody Shipment shipment) {
        Shipment created = shipmentService.create(shipment);
        return ResponseEntity.ok(created);
    }


    @GetMapping("/read/{shipmentId}")
    public ResponseEntity<Shipment> read(@PathVariable("shipmentId") Long shipmentId) {
        Shipment shipment = shipmentService.read(shipmentId);
        return ResponseEntity.ok(shipment);
    }


    @PutMapping("/update")
    public Shipment update(@RequestBody Shipment shipment) {
        return shipmentService.update(shipment);
    }

    @DeleteMapping("/delete/{shipmentId}")
    public boolean delete(@PathVariable("shipmentId") Long shipmentId) {
        return shipmentService.delete(shipmentId);
    }

    @GetMapping("/getall")
    public List<Shipment> getAll() {
        return shipmentService.getAll();
    }
}

