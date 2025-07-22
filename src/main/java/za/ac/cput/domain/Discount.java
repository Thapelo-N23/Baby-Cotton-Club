/*
 * Discount.java
 * Discount POJO class
 * Author: O Ntsaluba (230741754)
 * Date: 11 May 2025
 */

package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int discountId;
    private String discountName;
    private String discountType;
    private String discountValue;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToOne(mappedBy = "discount")
    private Order order;

    @OneToOne(mappedBy = "discount")
    private OrderLine orderLine;

    @OneToOne(mappedBy = "discount")
    private Product product;

    public Discount() {
    }

    public Discount(Builder builder) {
        this.discountId = builder.discountId;
        this.discountName = builder.discountName;
        this.discountType = builder.discountType;
        this.discountValue = builder.discountValue;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.order = builder.order;
        this.orderLine = builder.orderLine;
        this.product = builder.product;
    }

    public int getDiscountId() {
        return discountId;
    }

    public String getDiscountName() {
        return discountName;
    }

    public String getDiscountType() {
        return discountType;
    }

    public String getDiscountValue() {
        return discountValue;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Order getOrder() {
        return order;
    }

    public OrderLine getOrderLine() {
        return orderLine;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "Builder{" +
                "discountId=" + discountId +
                ", discountName='" + discountName + '\'' +
                ", discountType='" + discountType + '\'' +
                ", discountValue='" + discountValue + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", order=" + order +
                ", orderLine=" + orderLine +
                ", product=" + product +
                '}';
    }

    public static class Builder {
        private int discountId;
        private String discountName;
        private String discountType;
        private String discountValue;
        private LocalDate startDate;
        private LocalDate endDate;
        private Order order;
        private OrderLine orderLine;
        private Product product;

        public Builder setDiscountId(int discountId) {
            this.discountId = discountId;
            return this;
        }

        public Builder setDiscountName(String discountName) {
            this.discountName = discountName;
            return this;
        }

        public Builder setDiscountType(String discountType) {
            this.discountType = discountType;
            return this;
        }

        public Builder setDiscountValue(String discountValue) {
            this.discountValue = discountValue;
            return this;
        }

        public Builder setStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder setOrderLine(OrderLine orderLine) {
            this.orderLine = orderLine;
            return this;
        }

        public Builder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public Discount copy(Discount discount) {
            this.discountId = discount.discountId;
            this.discountName = discount.discountName;
            this.discountType = discount.discountType;
            this.discountValue = discount.discountValue;
            this.startDate = discount.startDate;
            this.endDate = discount.endDate;
            this.order = discount.order;
            this.orderLine = discount.orderLine;
            this.product = discount.product;
            return discount;
        }

        public Discount build() { return new Discount(this); }
    }
}