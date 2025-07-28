package za.ac.cput.factory;

import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderLine;
import za.ac.cput.factory.OrderLineFactory;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderFactory {

    public static Order createOrder(String orderDate, double totalAmount, List<OrderLine> orderLines, Customer customer) {

        LocalDate date = Helper.isValidDate(orderDate);

        Order order = new Order.Builder()
                .setOrderDate(date)
                .setTotalAmount(totalAmount)
                .setOrderLine(orderLines)
                .setCustomer(customer)
                .build();

        return order;
    }
}
