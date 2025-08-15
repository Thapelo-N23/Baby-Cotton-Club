package za.ac.cput.factory;

import za.ac.cput.domain.Customer;
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.domain.OrderLine;
import za.ac.cput.domain.Shipment;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.List;

public class CustomerOrderFactory {

    public static CustomerOrder createCustomerOrder(String orderDate, double totalAmount, List<OrderLine> orderLines, Customer customer, Shipment shipment) {

        LocalDate date = Helper.isValidDate(orderDate);



        return new CustomerOrder.Builder()
                .setOrderDate(date)
                .setTotalAmount(totalAmount)
                .setOrderLines(orderLines)
                .setCustomer(customer)
                .setShipment(shipment)
                .build();

    }
}
