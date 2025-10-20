package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customer_order")
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int orderId;

    protected LocalDate orderDate;
    protected double totalAmount;

    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL)
    @JsonManagedReference("order-lines")
    private List<OrderLine> orderLines;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference("customer-orders")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    @JsonBackReference("shipment-orders")
    private Shipment shipment;

    private String status;

    protected CustomerOrder() {}

    private CustomerOrder(Builder builder) {
        this.orderId = builder.orderId;
        this.orderDate = builder.orderDate;
        this.totalAmount = builder.totalAmount;
        this.orderLines = builder.orderLines;
        this.customer = builder.customer;
        this.shipment = builder.shipment;
        this.status = builder.status;
    }

    public int getOrderId() {
        return orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                ", orderLines=" + (orderLines != null ? orderLines.size() + " lines" : "null") +
                ", customerId=" + (customer != null ? customer.getCustomerId() : "null") +
                ", shipmentId=" + (shipment != null ? shipment.getShipmentId() : "null") +
                ", status='" + status + '\'' +
                '}';
    }
    public static class Builder {
        private int orderId;
        private LocalDate orderDate;
        private double totalAmount;
        private List<OrderLine> orderLines;
        private Customer customer;
        private Shipment shipment;
        private String status;

        public Builder setOrderId(int orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder setOrderDate(LocalDate orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public Builder setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder setOrderLines(List<OrderLine> orderLines) {
            this.orderLines = orderLines;
            return this;
        }

        public Builder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }
        public Builder setShipment(Shipment shipment) {
            this.shipment = shipment;
            return this;
        }
        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder copy(CustomerOrder customerOrder) {
            this.orderId = customerOrder.orderId;
            this.orderDate = customerOrder.orderDate;
            this.totalAmount = customerOrder.totalAmount;
            this.orderLines = customerOrder.orderLines;
            this.customer = customerOrder.customer;
            this.shipment = customerOrder.shipment;
            this.status = customerOrder.status;
            return this;
        }

        public CustomerOrder build() {
            return new CustomerOrder(this);
        }
    }
}
