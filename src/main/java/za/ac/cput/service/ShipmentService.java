package za.ac.cput.service;

import za.ac.cput.domain.Shipment;

import java.util.List;

public interface ShipmentService extends IService<Shipment, Long> {
    boolean delete(Long shipmentId);
    List<Shipment> getAll();
}
