/**
 * BabyCottonClub
 * Shipment.java
 * Author : Thapelo Ngwenya - 222260971
 * Date : 11 May 2025
 */


package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Shipment;
import za.ac.cput.service.ShipmentService;

import java.util.List;
@RestController
@RequestMapping("shipment")
public class ShipmentController {
    private ShipmentService shipmentService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }
    @PostMapping("create")
    public Shipment create(@RequestBody Shipment shipment) {
        return shipmentService.create(shipment);
    }
    @GetMapping("/read{shipmentId}")
    public Shipment read(@PathVariable("shipmentId") String shipmentId) {
        return shipmentService.read(shipmentId);
    }

    @PutMapping("/update")
    public Shipment update(@RequestBody Shipment shipment) {
        return shipmentService.update(shipment);
    }

    @GetMapping("/getall")
    public List<Shipment> getall() {
        return shipmentService.getAll();
    }
}

