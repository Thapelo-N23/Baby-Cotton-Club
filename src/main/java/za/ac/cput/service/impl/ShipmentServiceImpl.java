package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Shipment;
import za.ac.cput.repository.ShipmentRepository;
import za.ac.cput.service.ShipmentService;

import java.util.List;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;

    @Autowired
    public ShipmentServiceImpl(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public Shipment create(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    @Override
    public Shipment read(Long shipmentId) {
        return shipmentRepository.findById(shipmentId).orElse(null);
    }

    @Override
    public Shipment update(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    @Override
    public boolean delete(Long shipmentId) {
        if (shipmentRepository.existsById(shipmentId)) {
            shipmentRepository.deleteById(shipmentId);
            return true;
        }
        return false;
    }

    @Override
    public List<Shipment> getAll() {
        return shipmentRepository.findAll();
    }
}
