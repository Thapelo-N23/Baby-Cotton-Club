/*
Baby Cotton Club
OrderLine POJO Class
Author: Tsireledzo Netshilonwe
Student Number: 230666426
Date: 2025/05/10
*/

package za.ac.cput.domain;

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

    @ManyToOne
    @JoinColumn(name = "order_id")
    protected CustomerOrder customerOrder;
    @ManyToOne
    @JoinColumn(name = "product_id")
    protected Product product;
    @OneToOne
    @JoinColumn(name = "discount_id")
    protected Discount discount;

    protected OrderLine() {}

    private OrderLine(Builder builder) {
        this.orderLineId = builder.orderLineId;
        this.quantity = builder.quantity;
        this.unitPrice = builder.unitPrice;
        this.subTotal = builder.subTotal;
        this.customerOrder = builder.customerOrder;
        this.product = builder.product;
        this.discount = builder.discount;
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
    public Discount getDiscount() {
        return discount; }

    @Override
    public String toString() {
        return "OrderLine{" +
                "orderLineId=" + orderLineId +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", subTotal=" + subTotal +
                ", customerOrderId=" + (customerOrder != null ? customerOrder.getOrderId() : "null") +
                ", productId=" + (product != null ? product.getProductId() : "null") +
                ", discountId=" + (discount != null ? discount.getDiscountId() : "null") +
                '}';
    }


    public static class Builder {
        private int orderLineId;
        private int quantity;
        private double unitPrice;
        private double subTotal;
        private CustomerOrder customerOrder;
        private Product product;
        private Discount discount;

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
        public Builder setDiscount(Discount discount) {
            this.discount = discount;
            return this; }

        public Builder copy(OrderLine orderLine) {
            this.orderLineId = orderLine.orderLineId;
            this.quantity = orderLine.quantity;
            this.unitPrice = orderLine.unitPrice;
            this.subTotal = orderLine.subTotal;
            this.customerOrder = orderLine.customerOrder;
            this.product = orderLine.product;
            this.discount = orderLine.discount;
            return this;
        }

        public OrderLine build() { return new OrderLine(this); }
    }
}