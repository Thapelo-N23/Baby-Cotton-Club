
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
import za.ac.cput.service.impl.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    private final SupplierService service;

    @Autowired
    public SupplierController(SupplierService supplierService){
        this.service = supplierService;
    }

    @PostMapping("/create")
    public Supplier createSupplier(@RequestBody Supplier supplier){
        return service.create(supplier);
    }

    @GetMapping("/read/{id}")
    public Supplier readSupplier(@PathVariable("id") Integer id){
        return service.read(id);
    }

    @PutMapping("/update")
    public Supplier updateSupplier(@RequestBody Supplier supplier){
        return service.update(supplier);
    }

    @GetMapping("/getall")
    public List<Supplier> getAllSuppliers(){
        return service.getAll();
    }
}

