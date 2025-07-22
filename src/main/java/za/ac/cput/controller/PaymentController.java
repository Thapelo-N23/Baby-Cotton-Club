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
    private PaymentService service;

    @Autowired
    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Payment createPayment(@RequestBody Payment payment) {
        return service.create(payment);
    }

    @GetMapping("/read/{id}")
    public Payment readPayment(@PathVariable String id) {
        return service.read(id);
    }

    @PutMapping("/update")
    public Payment updatePayment(@RequestBody Payment payment) {
        return service.update(payment);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deletePayment(@PathVariable String id) {
        return service.delete(id);
    }

    @GetMapping("/getall")
    public List<Payment> getAllPayments() {
        return service.getAll();
    }
}

