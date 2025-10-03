/*
Baby Cotton Club
CustomerOrderController
Author: Tsireledzo Netshilonwe
Student Number: 230666426
Date: 2025/05/24
*/
package za.ac.cput.controller;

import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.service.impl.CustomerOrderService;
import za.ac.cput.dto.CustomerOrderRequest;
import za.ac.cput.domain.Customer;
import za.ac.cput.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class CustomerOrderController {

    private final CustomerOrderService service;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerOrderController(CustomerOrderService service, CustomerRepository customerRepository) {
        this.service = service;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/create")
    public CustomerOrder create(@RequestBody CustomerOrderRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow(() -> new RuntimeException("Customer not found"));
        CustomerOrder order = new CustomerOrder.Builder()
                .setOrderDate(request.getOrderDate())
                .setTotalAmount(request.getTotalAmount())
                .setCustomer(customer)
                .setStatus(request.getStatus())

                .build();
        return service.create(order);
    }

    @GetMapping("/read/{orderId}")
    public CustomerOrder read(@PathVariable ("orderId")Integer orderId) {
        return service.read(orderId);
    }

    @PutMapping("/update")
    public CustomerOrder update(@RequestBody CustomerOrderRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow(() -> new RuntimeException("Customer not found"));
        CustomerOrder order = new CustomerOrder.Builder()
                .setOrderDate(request.getOrderDate())
                .setTotalAmount(request.getTotalAmount())
                .setCustomer(customer)
                .setStatus(request.getStatus())
                // Add orderLines, shipment, admin if needed
                .build();
        return service.update(order);
    }

    @GetMapping("/getall")
    public List<CustomerOrder> getAllOrders() {
        return service.getAll();
    }
}