
/*
Baby Cotton Club
OrderController
Author: Tsireledzo Netshilonwe
Student Number: 230666426
Date: 2025/05/24
*/
package za.ac.cput.controller;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Order;
import za.ac.cput.service.impl.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orderline")

public class OrderController {
    private OrderService service;
    public OrderController(OrderService service) {

        this.service = service;
    }
    @PostMapping("/create")
    public Order create(@RequestBody Order order) {

        return service.create(order);
    }
    @GetMapping("/read/{id}")
    public Order read(@PathVariable String orderId) {

        return service.read(orderId);
    }
    @PutMapping("/update")
    public Order update(@RequestBody Order order) {

        return service.update(order);
    }


    @GetMapping("/getall")
    public List<Order> getAllOrders() {
        return service.getAll();

    }

}
