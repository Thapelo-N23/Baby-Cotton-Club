/*
Baby Cotton Club
OrderLineController
Author: Tsireledzo Netshilonwe
Student Number: 230666426
Date: 2025/05/24
*/
package za.ac.cput.controller;


import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.OrderLine;
import za.ac.cput.service.OrderLineService;

import java.util.List;

@RestController
@RequestMapping("/orderline")
public class OrderLineController {

    private OrderLineService service;
    public OrderLineController(OrderLineService service) {
        this.service = service;
    }
    @PostMapping("/create")
    public OrderLine create(@RequestBody OrderLine orderLine)
    {
        return service.create(orderLine);
    }

    @GetMapping("/read")
    public OrderLine read(@PathVariable String orderLineId)
    {
        return service.read(orderLineId);
    }
    @PutMapping("/update")
    public OrderLine update(@RequestBody OrderLine orderLine)
    {

        return service.update(orderLine);

    }
    @GetMapping("/getall")


    public List<OrderLine> getAll()
    {
        return service.getAll();
    }

}
