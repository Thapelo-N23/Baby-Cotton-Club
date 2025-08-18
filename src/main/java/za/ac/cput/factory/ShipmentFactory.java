/**
 * BabyCottonClub
 * Shipment.java
 * Author : Thapelo Ngwenya - 222260971
 * Date : 11 May 2025
 */


package za.ac.cput.factory;

import org.springframework.core.annotation.Order;
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.domain.OrderLine;
import za.ac.cput.domain.Shipment;
import za.ac.cput.util.Helper;

import java.util.List;

public class ShipmentFactory {
    public static Shipment createShipment(String carrierName, String shipmentStatus, double shippingCost, List<CustomerOrder> customerOrders , List<OrderLine>orderLines) {
        if (
                Helper.isNullOrEmpty(carrierName) ||
                Helper.isNullOrEmpty(shipmentStatus)) {
            return null;
        }

        return new Shipment.Builder()
                .setCarrierName(carrierName)
                .setShipmentStatus(shipmentStatus)
                .setShippingCost(shippingCost)
                .setCustomerOrder(customerOrders)
                .build();

    }

}
//updated
