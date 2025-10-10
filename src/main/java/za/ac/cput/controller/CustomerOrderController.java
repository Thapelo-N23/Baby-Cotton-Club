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
import za.ac.cput.domain.OrderLine;
import za.ac.cput.repository.CustomerRepository;
import za.ac.cput.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import za.ac.cput.dto.CustomerOrderResponse;
import za.ac.cput.mapper.CustomerOrderMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.dto.OrderStatusUpdateRequest;

import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/order")
public class CustomerOrderController {

    private final CustomerOrderService service;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CustomerOrderController(CustomerOrderService service, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.service = service;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @PostMapping("/create")
    public CustomerOrderResponse create(@RequestBody CustomerOrderRequest request) {
        Integer customerId = request.getCustomerId();
        if (customerId == null || customerId <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid customerId: " + customerId);
        }
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found: " + customerId));
        CustomerOrder order = new CustomerOrder.Builder()
                .setOrderDate(request.getOrderDate())
                .setTotalAmount(request.getTotalAmount())
                .setCustomer(customer)
                .setStatus(request.getStatus())
                .build();

        // Build order lines from request with validation
        if (request.getOrderLines() != null && !request.getOrderLines().isEmpty()) {
            List<OrderLine> lines = request.getOrderLines().stream().map(olReq -> {
                Integer productId = olReq.getProductId();
                if (productId == null || productId <= 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid productId in order line: " + productId);
                }
                var product = productRepository.findById(productId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + productId));
                OrderLine line = new OrderLine.Builder()
                        .setQuantity(olReq.getQuantity())
                        .setUnitPrice(olReq.getUnitPrice())
                        .setSubTotal(olReq.getSubTotal())
                        .setProduct(product)
                        .setOrder(order)
                        .build();
                return line;
            }).toList();
            order.setOrderLines(lines);
        }
        return CustomerOrderMapper.toDto(service.create(order));
    }

    @GetMapping("/read/{orderId}")
    public CustomerOrderResponse read(@PathVariable ("orderId")Integer orderId) {
        return CustomerOrderMapper.toDto(service.read(orderId));
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

    @PatchMapping("/status/{orderId}")
    public ResponseEntity<CustomerOrderResponse> updateOrderStatus(
        @PathVariable("orderId") Integer orderId,
        @RequestBody OrderStatusUpdateRequest request
    ) {
        CustomerOrder updatedOrder = service.updateOrderStatus(orderId, request.getStatus());
        if (updatedOrder == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(CustomerOrderMapper.toDto(updatedOrder));
    }

    @GetMapping("/getall")
    public List<CustomerOrderResponse> getAllOrders() {
        return service.getAll().stream().map(CustomerOrderMapper::toDto).toList();
    }

    @GetMapping("/customer/{customerId}")
    public List<CustomerOrderResponse> getOrdersByCustomerId(@PathVariable("customerId") Integer customerId) {
        if (customerId == null || customerId <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid customerId: " + customerId);
        }
        // Optionally check if customer exists
        if (!customerRepository.existsById(customerId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found: " + customerId);
        }
        List<CustomerOrder> orders = service.getOrdersByCustomerId(customerId);
        return orders.stream().map(CustomerOrderMapper::toDto).toList();
    }
}