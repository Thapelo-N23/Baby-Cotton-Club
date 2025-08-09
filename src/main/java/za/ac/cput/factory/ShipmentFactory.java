/**
 * BabyCottonClub
 * Shipment.java
 * Author : Thapelo Ngwenya - 222260971
 * Date : 11 May 2025
 */


package za.ac.cput.factory;

import za.ac.cput.domain.Shipment;
import za.ac.cput.util.Helper;

public class ShipmentFactory {
    public static Shipment createShipment(Long shipmentId, String carrierName, String shipmentStatus, double shippingCost) {
        if (!Helper.isValidShipmentId(shipmentId) ||
                Helper.isNullOrEmpty(carrierName) ||
                Helper.isNullOrEmpty(shipmentStatus)) {
            return null;
        }

        return new Shipment.Builder()
                .setShipmentId(shipmentId) // no need to wrap in Long.valueOf()
                .setCarrierName(carrierName)
                .setShipmentStatus(shipmentStatus)
                .setShippingCost(shippingCost)
                .build();
    }

}
//updated
