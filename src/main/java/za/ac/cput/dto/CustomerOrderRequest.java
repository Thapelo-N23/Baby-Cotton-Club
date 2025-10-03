package za.ac.cput.dto;

import java.time.LocalDate;
import java.util.List;

public class CustomerOrderRequest {
    private int customerId;
    private LocalDate orderDate;
    private double totalAmount;
    private List<Integer> orderLineIds; // Or order line DTOs if needed
    private Integer shipmentId;
    private Integer adminId;
    private String status;

    // Getters and setters
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public List<Integer> getOrderLineIds() { return orderLineIds; }
    public void setOrderLineIds(List<Integer> orderLineIds) { this.orderLineIds = orderLineIds; }
    public Integer getShipmentId() { return shipmentId; }
    public void setShipmentId(Integer shipmentId) { this.shipmentId = shipmentId; }
    public Integer getAdminId() { return adminId; }
    public void setAdminId(Integer adminId) { this.adminId = adminId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

