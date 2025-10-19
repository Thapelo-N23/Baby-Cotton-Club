package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.OrderLine;
import za.ac.cput.service.impl.OrderLineService;
import za.ac.cput.dto.OrderLineRequest;
import za.ac.cput.repository.CustomerOrderRepository;
import za.ac.cput.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.domain.Product;

@RestController
@RequestMapping("/api/orderline")
public class OrderLineController {

    private final OrderLineService service;

    @Autowired
    public OrderLineController(OrderLineService service) {
        this.service = service;
    }

    @Autowired
    private CustomerOrderRepository customerOrderRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/create")
    public ResponseEntity<OrderLine> create(@RequestBody OrderLineRequest req) {
        CustomerOrder customerOrder = customerOrderRepository.findById(req.getOrderId()).orElse(null);
        Product product = productRepository.findById(req.getProductId()).orElse(null);
        if (customerOrder == null || product == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        OrderLine orderLine = new OrderLine.Builder()
                .setOrder(customerOrder) // Use correct builder method for order
                .setProduct(product)
                .setQuantity(req.getQuantity())
                .setUnitPrice(req.getUnitPrice())
                .setSize(req.getSize())
                .build();
        OrderLine created = service.create(orderLine);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
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
