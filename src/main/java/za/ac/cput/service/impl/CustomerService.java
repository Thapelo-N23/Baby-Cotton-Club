/**
 * BabyCottonClub
 * Product.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date : 24 May 2025
 */


package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Customer;
import za.ac.cput.repository.CustomerRepository;
import za.ac.cput.service.ICustomerService;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public Customer create(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public Customer read(Integer s) {
        return this.customerRepository.findById(s).orElse(null);
    }

    @Override
    public Customer update(Customer customer) {
        return this.customerRepository.save(customer);
    }
    @Override
    public List<Customer> getAll() {
        return this.customerRepository.findAll();
    }

}
