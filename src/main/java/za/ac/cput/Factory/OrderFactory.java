/*
OrderFactory Class
Author: Tsireledzo Netshilonwe
Student Number: 230666426
Date: 2025/05/10
*/
package za.ac.cput.factory;


import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderLine;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.Arrays;

public class OrderFactory {

    public static Order createOrder( String orderDate, double totalAmount,int quantity, double unitPrice) {


        LocalDate date = Helper.isValidDate(orderDate);

        OrderLine orderLine = OrderLineFactory.createOrderLine(quantity, unitPrice);


        return new Order.Builder()
                .setOrderDate(date)
                .setTotalAmount(totalAmount)
                .setOrderLines(Arrays.asList(orderLine))
                .build();

    }
}
