package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.OrderLine;
import za.ac.cput.service.impl.OrderLineService;

import java.util.List;

@RestController
@RequestMapping("/api/orderline")
public class OrderLineController {

    private final OrderLineService service;

    @Autowired
    public OrderLineController(OrderLineService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public OrderLine create(@RequestBody OrderLine orderLine) {
        return service.create(orderLine);
    }

    @GetMapping("/read/{orderLineId}")
    public OrderLine read(@PathVariable("orderLineId") int orderLineId) {
        return service.read(orderLineId);
    }

    @PutMapping("/update")
    public OrderLine update(@RequestBody OrderLine orderLine) {
        return service.update(orderLine);
    }

    @GetMapping("/getall")
    public List<OrderLine> getAll() {
        return service.getAll();
    }
}
