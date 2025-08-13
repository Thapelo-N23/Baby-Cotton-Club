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
    private OrderLine orderLine;

    public Discount() {
    }

    public Discount(Builder builder) {
        this.discountId = builder.discountId;
        this.discountName = builder.discountName;
        this.discountType = builder.discountType;
        this.discountValue = builder.discountValue;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.orderLine = builder.orderLine;
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

    public OrderLine getOrderLine() {
        return orderLine;
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
                ", orderLineId=" + (orderLine != null ? orderLine.getOrderLineId() : "null") +
                '}';
    }

    public static class Builder {
        private int discountId;
        private String discountName;
        private String discountType;
        private String discountValue;
        private LocalDate startDate;
        private LocalDate endDate;
        private OrderLine orderLine;

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

        public Builder setOrderLine(OrderLine orderLine) {
            this.orderLine = orderLine;
            return this;
        }


        public Discount copy(Discount discount) {
            this.discountId = discount.discountId;
            this.discountName = discount.discountName;
            this.discountType = discount.discountType;
            this.discountValue = discount.discountValue;
            this.startDate = discount.startDate;
            this.endDate = discount.endDate;
            this.orderLine = discount.orderLine;
            return discount;
        }

        public Discount build() { return new Discount(this); }
    }
}