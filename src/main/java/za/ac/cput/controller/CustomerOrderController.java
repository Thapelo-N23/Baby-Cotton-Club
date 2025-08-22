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

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class CustomerOrderController {

    private final CustomerOrderService service;

    public CustomerOrderController(CustomerOrderService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public CustomerOrder create(@RequestBody CustomerOrder customerOrder) {
        return service.create(customerOrder);
    }

    @GetMapping("/read/{orderId}")
    public CustomerOrder read(@PathVariable ("orderId")Integer orderId) {
        return service.read(orderId);
    }

    @PutMapping("/update")
    public CustomerOrder update(@RequestBody CustomerOrder customerOrder) {
        return service.update(customerOrder);
    }

    @GetMapping("/get all")
    public List<CustomerOrder> getAllOrders() {
        return service.getAll();
    }
}