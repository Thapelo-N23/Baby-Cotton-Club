/*
 * Discount.java
 * Discount POJO class
 * Author: O Ntsaluba (230741754)
 * Date: 11 May 2025
 */

package za.ac.cput.domain;

import java.time.LocalDate;
import java.util.Date;

public class Discount {
    private int discountId;
    private String discountName;
    private String discountType;
    private String discountValue;
    private LocalDate date;


    public Discount() {
    }

    public Discount(Builder builder) {
        this.discountId = builder.discountId;
        this.discountName = builder.discountName;
        this.discountType = builder.discountType;
        this.discountValue = builder.discountValue;
        this.date = builder.date;
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
//
//    public LocalDate getDate() {
//        return date;
//    }

    public LocalDate getLocalDate() {
        return date;
    }


    public static class Builder {
        private int discountId;
        private String discountName;
        private String discountType;
        private String discountValue;
        private LocalDate date;


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

        public Builder setLocalDate( LocalDate date) {
            this.date = date;
            return this;
        }


        @Override

        public String toString() {
            return "Builder{" +
                    "discountId=" + discountId +
                    ", discountName='" + discountName + '\'' +
                    ", discountType='" + discountType + '\'' +
                    ", discountValue='" + discountValue + '\'' +
                    ", date=" + date +
                    '}';
        }

        public Discount build() {
            return new Discount(this);
        }
    }

        public Discount copy(Discount discount) {
            this.discountId = discount.discountId;
            this.discountName = discount.discountName;
            this.discountType = discount.discountType;
            this.discountValue = discount.discountValue;
            this.date = discount.date;
            return this;
        }


    }
