
/*
SupplierController POJO Class
Author: Phindile Lisa Ngozi
Student Number: 230640893
Date: 2025/05/25
 */
package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Supplier;
import za.ac.cput.service.SupplierService;

import java.util.List;

    @RestController
    @RequestMapping("/supplier")
    public class SupplierController {
        private SupplierService service;

        @Autowired
        public SupplierController(SupplierService service) {
            this.service = service;
        }

        @PostMapping("/create")
        public Supplier createSupplier(@RequestBody Supplier supplier) {
            return service.create(supplier);
        }

        @GetMapping("/read/{id}")
        public Supplier readSupplier(@PathVariable String id) {
            return service.read(id);
        }

        @PutMapping("/update")
        public Supplier updateSupplier(@RequestBody Supplier supplier) {
            return service.update(supplier);
        }

        @DeleteMapping("/delete/{id}")
        public boolean deleteSupplier(@PathVariable String id) {
            return service.delete(id);
        }

        @GetMapping("/getall")
        public List<Supplier> getAllSuppliers() {
            return service.getAll();
        }
    }


