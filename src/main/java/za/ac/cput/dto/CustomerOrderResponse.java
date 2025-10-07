package za.ac.cput.dto;

import java.time.LocalDate;
import java.util.List;

public class CustomerOrderResponse {
    private int orderId;
    private LocalDate orderDate;
    private double totalAmount;
    private String status;
    private List<OrderLineResponse> orderLines;

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<OrderLineResponse> getOrderLines() { return orderLines; }
    public void setOrderLines(List<OrderLineResponse> orderLines) { this.orderLines = orderLines; }
}
