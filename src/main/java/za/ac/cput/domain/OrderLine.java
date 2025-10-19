/*
Baby Cotton Club
OrderLine POJO Class
Author: Tsireledzo Netshilonwe
Student Number: 230666426
Date: 2025/05/10
*/

package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "order_line")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int orderLineId;
    protected int quantity;
    protected double unitPrice;
    protected double subTotal;

    // new size field
    @Column(name = "size")
    protected String size;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference("order-lines")
    protected CustomerOrder customerOrder;
    @ManyToOne
    @JoinColumn(name = "product_id")
    protected Product product;


    protected OrderLine() {}

    private OrderLine(Builder builder) {
        this.orderLineId = builder.orderLineId;
        this.quantity = builder.quantity;
        this.unitPrice = builder.unitPrice;
        this.subTotal = builder.subTotal;
        this.size = builder.size;
        this.customerOrder = builder.customerOrder;
        this.product = builder.product;

    }

    public int getOrderLineId() {
        return orderLineId; }
    public int getQuantity() {
        return quantity; }
    public double getUnitPrice() {
        return unitPrice; }
    public double getSubTotal() {
        return subTotal; }
    public CustomerOrder getOrder() {
        return customerOrder; }
    public Product getProduct() {
        return product; }
    public String getSize() { return size; }


    @Override
    public String toString() {
        return "OrderLine{" +
                "orderLineId=" + orderLineId +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", subTotal=" + subTotal +
                ", size='" + size + '\'' +
                ", customerOrderId=" + (customerOrder != null ? customerOrder.getOrderId() : "null") +
                ", productId=" + (product != null ? product.getProductId() : "null") +

                '}';
    }


    public static class Builder {
        private int orderLineId;
        private int quantity;
        private double unitPrice;
        private double subTotal;
        private String size;
        private CustomerOrder customerOrder;
        private Product product;


        public Builder setOrderLineId(int orderLineId) {
            this.orderLineId = orderLineId;
            return this; }
        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this; }
        public Builder setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
            return this; }
        public Builder setSubTotal(double subTotal) {
            this.subTotal = subTotal;
            return this; }
        public Builder setOrder(CustomerOrder customerOrder) {
            this.customerOrder = customerOrder;
            return this; }
        public Builder setProduct(Product product) {
            this.product = product;
            return this; }
        public Builder setSize(String size) {
            this.size = size;
            return this; }


        public Builder copy(OrderLine orderLine) {
            this.orderLineId = orderLine.orderLineId;
            this.quantity = orderLine.quantity;
            this.unitPrice = orderLine.unitPrice;
            this.subTotal = orderLine.subTotal;
            this.size = orderLine.size;
            this.customerOrder = orderLine.customerOrder;
            this.product = orderLine.product;
            return this;
        }

        public OrderLine build() { return new OrderLine(this); }
    }
}
