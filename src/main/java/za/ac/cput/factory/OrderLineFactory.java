/*
OrderLineFactory Class
Author: Tsireledzo Netshilonwe
Student Number: 230666426
Date: 2025/05/10
*/
package za.ac.cput.factory;

import za.ac.cput.domain.Discount;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderLine;
import za.ac.cput.domain.Product;
import za.ac.cput.util.Helper;

public class OrderLineFactory {

    // Simplified version without product, discount or order
    public static OrderLine createOrderLine(int quantity, double unitPrice) {
        double subTotal = Helper.calculateSubTotal(quantity, unitPrice);

        return new OrderLine.Builder()
                .setQuantity(quantity)
                .setUnitPrice(unitPrice)
                .setSubTotal(subTotal)
                .build();
    }

    // Full version with all associations
    public static OrderLine createOrderLine(int quantity, double unitPrice, Order order, Product product, Discount discount) {
        double subTotal = Helper.calculateSubTotal(quantity, unitPrice);

        return new OrderLine.Builder()
                .setQuantity(quantity)
                .setUnitPrice(unitPrice)
                .setSubTotal(subTotal)
                .setOrder(order)
                .setProduct(product)
                .setDiscount(discount)
                .build();
    }
}
