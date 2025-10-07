package za.ac.cput.mapper;

import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.domain.OrderLine;
import za.ac.cput.domain.Product;
import za.ac.cput.dto.CustomerOrderResponse;
import za.ac.cput.dto.OrderLineResponse;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerOrderMapper {
    public static CustomerOrderResponse toDto(CustomerOrder order) {
        if (order == null) return null;
        List<OrderLineResponse> orderLines = (order.getOrderLines() == null ? Collections.emptyList() : order.getOrderLines())
            .stream()
            .map(line -> CustomerOrderMapper.mapOrderLine((OrderLine) line))
            .collect(Collectors.toList());
        CustomerOrderResponse dto = new CustomerOrderResponse();
        dto.setOrderId(order.getOrderId());
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus());
        dto.setOrderLines(orderLines);
        return dto;
    }
    private static OrderLineResponse mapOrderLine(OrderLine line) {
        if (line == null) return null;
        OrderLineResponse r = new OrderLineResponse();
        r.setOrderLineId(line.getOrderLineId());
        r.setQuantity(line.getQuantity());
        r.setUnitPrice(line.getUnitPrice());
        r.setSubTotal(line.getSubTotal());
        Product p = line.getProduct();
        if (p != null) {
            r.setProductId(p.getProductId());
            r.setProductName(p.getProductName());
        }
        return r;
    }
}
