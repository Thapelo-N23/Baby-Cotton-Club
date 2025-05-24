/**
 * BabyCottonClub
 * Product.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date : 24 May 2025
 */
package za.ac.cput.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.domain.Customer;
import za.ac.cput.service.CustomerService;

@RestController
@RequestMapping("customer")

public class CustomerController {
    private CustomerService service;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.service = customerService;
    }
    @RequestMapping("/create")
    public Customer createCustomer(Customer customer) {
        return service.create(customer);
    }
    @GetMapping("/read")
    public Customer readCustomer(Integer customerId) {
        return service.read(customerId);
    }
    @RequestMapping("/update")
    public Customer updateCustomer(Customer customer) {
        return service.update(customer);
    }
    @GetMapping("/getall")
    public Iterable<Customer> getAllCustomers() {
        return service.getAll();
    }

}
