/*
 * BabyCottonClub
 * Shipment.java
 * Author : Thapelo Ngwenya - 222260971
 * Date : 11 May 2025
 */

package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "shipments")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shipmentId;

    private String carrierName;
    private String shipmentStatus;
    private double shippingCost;

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
    private List<OrderLine> orderLines;

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
    private List<CustomerOrder> customerOrder;

    public Shipment() {}

    private Shipment(Builder builder) {
        this.shipmentId = builder.shipmentId;
        this.carrierName = builder.carrierName;
        this.shipmentStatus = builder.shipmentStatus;
        this.shippingCost = builder.shippingCost;
        this.orderLines = builder.orderLines;
        this.customerOrder = builder.customerOrder;
    }

    public int getShipmentId() {
        return shipmentId;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public String getShipmentStatus() {
        return shipmentStatus;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public List<CustomerOrder> getCustomerOrder() {
        return customerOrder;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "shipmentId=" + shipmentId +
                ", carrierName='" + carrierName + '\'' +
                ", shipmentStatus='" + shipmentStatus + '\'' +
                ", shippingCost=" + shippingCost +
                ", orderLines=" + orderLines +
                ", customerOrder=" + customerOrder +
                '}';
    }

    public static class Builder {
        private int shipmentId;
        private String carrierName;
        private String shipmentStatus;
        private double shippingCost;
        private List<CustomerOrder> customerOrder;
        private List<OrderLine> orderLines;

        public Builder setShipmentId(int shipmentId) {
            this.shipmentId = shipmentId;
            return this;
        }

        public Builder setCarrierName(String carrierName) {
            this.carrierName = carrierName;
            return this;
        }

        public Builder setShipmentStatus(String shipmentStatus) {
            this.shipmentStatus = shipmentStatus;
            return this;
        }

        public Builder setShippingCost(double shippingCost) {
            this.shippingCost = shippingCost;
            return this;
        }

        public Builder setCustomerOrder(List<CustomerOrder> customerOrder) {
            this.customerOrder = customerOrder;
            return this;
        }

        public Builder setOrderLines(List<OrderLine> orderLines) {
            this.orderLines = orderLines;
            return this;
        }

        public Builder copy(Shipment shipment) {
            this.shipmentId = shipment.getShipmentId();
            this.carrierName = shipment.getCarrierName();
            this.shipmentStatus = shipment.getShipmentStatus();
            this.shippingCost = shipment.getShippingCost();
            this.orderLines = shipment.getOrderLines();
            this.customerOrder = shipment.getCustomerOrder();
            return this;
        }

        public Shipment build() {
            return new Shipment(this);
        }
    }
}
