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
    protected Long orderLineId;

    protected int quantity;
    protected double unitPrice;
    protected double subTotal;
    ;

    protected OrderLine() {
    }

    private OrderLine(Builder builder) {
        this.quantity = builder.quantity;
        this.unitPrice = builder.unitPrice;
        this.subTotal = builder.subTotal;

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


    @Override
    public String toString() {
        return "OrderLine{" +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", subTotal=" + subTotal +

                '}';
    }

    public static class Builder {

        private int quantity;
        private double unitPrice;
        private double subTotal;




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


        public Builder copy(OrderLine orderLine) {
            this.quantity = orderLine.quantity;
            this.unitPrice = orderLine.unitPrice;
            this.subTotal = orderLine.subTotal;
            return this;
        }

        public OrderLine build() {
            return new OrderLine(this);
        }
    }
}
