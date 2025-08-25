package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Shipment;
import za.ac.cput.service.ShipmentService;

import java.util.List;

@RestController
@RequestMapping("/shipment")
public class ShipmentController {
 private ShipmentService service;

 @Autowired
 public ShipmentController(ShipmentService service) {
  this.service = service;
 }

 @PostMapping("/create")
 public Shipment create(@RequestBody Shipment shipment) {
  return service.create(shipment);
 }

 @GetMapping("/read/{shipmentId}")
 public Shipment read(@PathVariable("shipmentId") int shipmentId) {
  return service.read(shipmentId);

 }

 @PutMapping("/update")
 public Shipment update(@RequestBody Shipment shipment) {
  return service.update(shipment);
 }

 @GetMapping("/getall")
 public List<Shipment> getall() {
  return service.getAll();
 }
}
//@DeleteMapping("/delete/{shipmentId}")
//    public boolean delete(@PathVariable int shipmentId) {
//        return
//}