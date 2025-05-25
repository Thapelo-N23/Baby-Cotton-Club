/**
 * BabyCottonClub
 * Shipment.java
 * Author : Thapelo Ngwenya - 222260971
 * Date : 11 May 2025
 */


package za.ac.cput.service;

import za.ac.cput.domain.Shipment;

import java.util.List;

public interface ShipmentService extends IService<Shipment, String> {
    List<Shipment>getAll();
}