/*
PaymentController POJO Class
Author: Phindile Lisa Ngozi
Student Number: 230640893
Date: 2025/05/25
 */
package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Payment;
import za.ac.cput.service.impl.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService service;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.service = paymentService;
    }

    @PostMapping("/create")
    public Payment createPayment(@RequestBody Payment payment) {
        return service.create(payment);
    }

    @GetMapping("/read/{id}")
    public Payment readPayment(@PathVariable("id") Integer id) {
        return service.read(id);
    }

    @PutMapping("/update")
    public Payment updatePayment(@RequestBody Payment payment) {
        return service.update(payment);
    }

    @GetMapping("/getall")
    public List<Payment> getAllPayments() {
        return service.getAll();
    }
}
