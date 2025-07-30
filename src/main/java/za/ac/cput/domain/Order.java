/*
Baby Cotton Club
Order POJO Class
Author: Tsireledzo Netshilonwe
Student Number: 230666426
Date: 2025/05/10
*/
package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int orderId;
    protected LocalDate orderDate;
    protected double totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLine> orderLine;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    protected Order() {}

    private Order(Builder builder) {
        this.orderId = builder.orderId;
        this.orderDate = builder.orderDate;
        this.totalAmount = builder.totalAmount;
        this.orderLine = builder.orderLine;
        this.customer = builder.customer;
    }

    public int getOrderId() {
        return orderId; }
    public LocalDate getOrderDate() {
        return orderDate; }
    public double getTotalAmount() {
        return totalAmount; }
    public List<OrderLine> getOrderLine() {
        return orderLine; }
    public Customer getCustomer() {
        return customer; }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                ", orderLines=" + orderLine +
                ", customer=" + customer +
                '}';
    }

    public static class Builder {
        private int orderId;
        private LocalDate orderDate;
        private double totalAmount;
        private List<OrderLine> orderLine;
        private Customer customer;

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
        public Builder setOrderLine(List<OrderLine> orderLine) {
            this.orderLine = orderLine;
            return this;
        }
        public Builder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }
        public Builder copy(Order order) {
            this.orderId = order.orderId;
            this.orderDate = order.orderDate;
            this.totalAmount = order.totalAmount;
            this.orderLine = order.orderLine;
            this.customer = order.customer;
            return this;
        }
        public Order build() {
            return new Order(this);
        }
    }
}

