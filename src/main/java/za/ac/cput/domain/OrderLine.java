/*
Baby Cotton Club
OrderLine POJO Class
Author: Tsireledzo Netshilonwe
Student Number: 230666426
Date: 2025/05/10
*/

package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "order_lines")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long orderLineId;
    protected int quantity;
    protected double unitPrice;
    protected double subTotal;

    @ManyToOne
    @JoinColumn(name = "order_id")
    protected Order order;
    @ManyToOne
    @JoinColumn(name = "product_id")
    protected Product product;
    @OneToOne
    @JoinColumn(name = "discount_id")
    protected Discount discount;

    protected OrderLine() {}

    private OrderLine(Builder builder) {
        this.quantity = builder.quantity;
        this.unitPrice = builder.unitPrice;
        this.subTotal = builder.subTotal;
        this.order = builder.order;
        this.product = builder.product;
        this.discount = builder.discount;
    }

    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }
    public double getSubTotal() { return subTotal; }
    public Order getOrder() { return order; }
    public Product getProduct() { return product; }
    public Discount getDiscount() { return discount; }

    @Override
    public String toString() {
        return "OrderLine{" +
                "orderLineId=" + orderLineId +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", subTotal=" + subTotal +
                ", order=" + (order != null ? order.getOrderId() : null) +
                ", product=" + (product != null ? product.getProductId() : null) +
                ", discount=" + (discount != null ? discount.getDiscountId() : null) +
                '}';
    }

    public static class Builder {
        private int quantity;
        private double unitPrice;
        private double subTotal;
        private Order order;
        private Product product;
        private Discount discount;

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this; }
        public Builder setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
            return this; }
        public Builder setSubTotal(double subTotal) {
            this.subTotal = subTotal;
            return this; }
        public Builder setOrder(Order order) {
            this.order = order;
            return this; }
        public Builder setProduct(Product product) {
            this.product = product;
            return this; }
        public Builder setDiscount(Discount discount) {
            this.discount = discount;
            return this; }

        public Builder copy(OrderLine orderLine) {
            this.quantity = orderLine.quantity;
            this.unitPrice = orderLine.unitPrice;
            this.subTotal = orderLine.subTotal;
            this.order = orderLine.order;
            this.product = orderLine.product;
            this.discount = orderLine.discount;
            return this;
        }

        public OrderLine build() { return new OrderLine(this); }
    }
}
