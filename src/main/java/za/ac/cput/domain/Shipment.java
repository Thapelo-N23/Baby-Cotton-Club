/**
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
    private Long shipmentId;
    protected String carrierName;
    protected String shipmentStatus;
    protected double shippingCost;

    @OneToMany(mappedBy = "shipment")
    private List<Product>products;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "orderId")
    private Order order;

    public Shipment() {
    }

    private Shipment(Builder builder) {
        this.shipmentId = Long.valueOf(builder.shipmentId);
        this.carrierName = builder.carrierName;
        this.shipmentStatus = builder.shipmentStatus;
        this.shippingCost = builder.shippingCost;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public Order getOrder() {
        return order;
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

    @Override
    public String toString() {
        return "Shipment{" +
                "shipmentId=" + shipmentId +
                ", carrierName='" + carrierName + '\'' +
                ", shipmentStatus='" + shipmentStatus + '\'' +
                ", shippingCost=" + shippingCost +
                ", products=" + products +
                ", order=" + order +
                '}';
    }

    public static class Builder {
        private Long shipmentId;
        private String carrierName;
        private String shipmentStatus;
        private double shippingCost;

        public Builder setShipmentId(Long shipmentId) {
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

        @Override
        public String toString() {
            return "Builder{" +
                    "shipmentId='" + shipmentId + '\'' +
                    ", carrierName='" + carrierName + '\'' +
                    ", shipmentStatus='" + shipmentStatus + '\'' +
                    ", shippingCost=" + shippingCost +
                    '}';
        }

    public Builder copy (Shipment shipment){
            this.shipmentId = shipment.getShipmentId();
            this.carrierName = shipment.getCarrierName();
            this.shipmentStatus = shipment.getShipmentStatus();
            this.shippingCost = shipment.getShippingCost();
            return this;

    }
    public Shipment build() {
            return new Shipment(this);
}
    }

    }

