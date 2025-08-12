
/*
Baby Cotton Club
OrderController
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
@RequestMapping("/order")

public class CustomerOrderController {
    private CustomerOrderService service;
    public CustomerOrderController(CustomerOrderService service) {

        this.service = service;
    }
    @PostMapping("/create")
    public CustomerOrder create(@RequestBody CustomerOrder customerOrder) {

        return service.create(customerOrder);
    }
    @GetMapping("/read/{id}")
    public CustomerOrder read(@PathVariable Integer orderId) {

        return service.read(orderId);
    }
    @PutMapping("/update")
    public CustomerOrder update(@RequestBody CustomerOrder customerOrder) {

        return service.update(customerOrder);
    }


    @GetMapping("/getall")
    public List<CustomerOrder> getAllOrders() {
        return service.getAll();

    }

}
