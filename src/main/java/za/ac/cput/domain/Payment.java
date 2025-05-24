/*
Payment POJO Class
Author: Phindile Lisa Ngozi
Student Number: 230640893
Date: 2025/05/11
 */
package za.ac.cput.domain;

import java.time.LocalDate;

public class Payment {
    private String paymentId;
    private String orderId;
    private LocalDate paymentDate;
    private String paymentMethod;
    private String paymentStatus;
    private double amount;

    public Payment() {

    }

    public Payment(Builder builder) {
        this.paymentId = builder.paymentId;
        this.orderId = builder.orderId;
        this.paymentDate = builder.paymentDate;
        this.paymentMethod = builder.paymentMethod;
        this.paymentStatus = builder.paymentStatus;
        this.amount = builder.amount;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", paymentDate=" + paymentDate +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", amount=" + amount +
                '}';
    }

    public static class Builder {
        private String paymentId;
        private String orderId;
        private LocalDate paymentDate;
        private String paymentMethod;
        private String paymentStatus;
        private double amount;

        public Builder setPaymentId(String paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Builder setOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder setPaymentDate(LocalDate paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public Builder setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        public Builder setAmount(double amount) {
            this.amount = amount;
            return this;
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "paymentId='" + paymentId + '\'' +
                    ", orderId='" + orderId + '\'' +
                    ", paymentDate=" + paymentDate +
                    ", paymentMethod='" + paymentMethod + '\'' +
                    ", paymentStatus='" + paymentStatus + '\'' +
                    ", amount=" + amount +
                    '}';
        }

        public Builder copy(Payment payment) {
            this.paymentId = payment.getPaymentId();
            this.orderId = payment.getOrderId();
            this.paymentDate = payment.getPaymentDate();
            this.paymentMethod = payment.getPaymentMethod();
            this.paymentStatus = payment.getPaymentStatus();
            this.amount = payment.getAmount();
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }
}

