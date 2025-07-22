/*
 * DiscountController POJO Class
 * Author: Onako Ntsaluba (230741754)
 * Date: 2025/05/25
 */
package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
        import za.ac.cput.domain.Discount;
import za.ac.cput.service.impl.DiscountService;

import java.util.List;

@RestController
@RequestMapping("/discount")
public class DiscountController {
    private DiscountService service;

    @Autowired
    public DiscountController(DiscountService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Discount createDiscount(@RequestBody Discount discount) {
        return service.create(discount);
    }

    @GetMapping("/read/{id}")
    public Discount readDiscount(@PathVariable Integer id) {
        return service.read(id);
    }

    @PutMapping("/update")
    public Discount updateDiscount(@RequestBody Discount discount) {
        return service.update(discount);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteDiscount(@PathVariable Integer id) {
        return service.delete(id);
    }

    @GetMapping("/getall")
    public List<Discount> getAllDiscounts() {
        return service.getAll();
    }
}