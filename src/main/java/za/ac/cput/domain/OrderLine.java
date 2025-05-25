/*
Baby Cotton Club
OrderLine POJO Class
Author: Tsireledzo Netshilonwe
Student Number: 230666426
Date: 2025/05/10
*/

package za.ac.cput.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

public class OrderLine {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    protected String orderLineId;
    protected String orderId;
    protected String productId;
    protected int quantity;
    protected double unitPrice;
    protected double subTotal;
    protected int discountId;

    protected   OrderLine(){

    }
    private OrderLine(Builder builder) {
        this.orderLineId = builder.orderLineId;
        this.orderId = builder.orderId;
        this.productId = builder.productId;
        this.quantity = builder.quantity;
        this.unitPrice = builder.unitPrice;
        this.subTotal = builder.subTotal;
        this.discountId = builder.discountId;
    }
    public String getOrderLineId() {
        return orderLineId;
    }
    public String getOrderId() {
        return orderId;
    }
    public String getProductId() {
        return productId;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
    public double getSubTotal() {
        return subTotal;
    }
    public int getDiscountId() {
        return discountId;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "orderLineId='" + orderLineId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", productId='" + productId + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", subTotal=" + subTotal +
                ", discountId=" + discountId +
                '}';
    }

    public static class Builder {
        private String orderLineId;
        private String orderId;
        private String productId;
        private int quantity;
        private double unitPrice;
        private double subTotal;
        private int discountId;

        public Builder setOrderLineId(String orderLineId) {
            this.orderLineId = orderLineId;
            return this;
        }

        public Builder setOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder setProductId(String productId) {
            this.productId = productId;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public Builder setSubTotal(double subTotal) {
            this.subTotal = subTotal;
            return this;
        }
        public Builder setDiscountId(int discountId) {
            this.discountId = discountId;
            return this;
        }
        public Builder copy(OrderLine orderLine) {
            this.orderLineId = orderLine.orderLineId;
            this.orderId = orderLine.orderId;
            this.productId = orderLine.productId;
            this.quantity = orderLine.quantity;
            this.unitPrice = orderLine.unitPrice;
            this.subTotal = orderLine.subTotal;
            this.discountId = orderLine.discountId;
            return this;
        }

        public OrderLine build() {
            return new OrderLine(this);
        }
    }
}


