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
    private List<CustomerOrder> customerOrder;

    public Shipment() {}

    private Shipment(Builder builder) {
        this.shipmentId = builder.shipmentId;
        this.carrierName = builder.carrierName;
        this.shipmentStatus = builder.shipmentStatus;
        this.shippingCost = builder.shippingCost;
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



    @Override
    public String toString() {
        return "Shipment{" +
                "shipmentId=" + shipmentId +
                ", carrierName='" + carrierName + '\'' +
                ", shipmentStatus='" + shipmentStatus + '\'' +
                ", shippingCost=" + shippingCost +
                ", customerOrder=" + customerOrder +
                '}';
    }

    public static class Builder {
        private int shipmentId;
        private String carrierName;
        private String shipmentStatus;
        private double shippingCost;
        private List<CustomerOrder> customerOrder;

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



        public Builder copy(Shipment shipment) {
            this.shipmentId = shipment.getShipmentId();
            this.carrierName = shipment.getCarrierName();
            this.shipmentStatus = shipment.getShipmentStatus();
            this.shippingCost = shipment.getShippingCost();
            this.customerOrder = shipment.getCustomerOrder();
            return this;
        }

        public Shipment build() {
            return new Shipment(this);
        }
    }
}