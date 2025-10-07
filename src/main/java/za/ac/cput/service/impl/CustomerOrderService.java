/*
 * Baby Cotton Club
 * OrderService.java
 * Author: Tsireledzo Netshilonwe
 * Student Number: 230666426
 * Date: 2025/05/24
 */

package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.repository.CustomerOrderRepository;
import za.ac.cput.service.ICustomerOrderService;

import java.util.List;

@Service
public class CustomerOrderService implements ICustomerOrderService {

    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    public CustomerOrderService(CustomerOrderRepository customerOrderRepository) {
        this.customerOrderRepository = customerOrderRepository;
    }

    @Override
    public CustomerOrder create(CustomerOrder customerOrder) {
        return this.customerOrderRepository.save(customerOrder);
    }

    @Override
    public CustomerOrder read(Integer id) {
        return this.customerOrderRepository.findById(id).orElse(null);
    }

    @Override
    public CustomerOrder update(CustomerOrder customerOrder) {
        return this.customerOrderRepository.save(customerOrder);
    }

    @Override
    public List<CustomerOrder> getAll() {
        return this.customerOrderRepository.findAll();
    }

    public List<CustomerOrder> getOrdersByCustomerId(Integer customerId) {
        return customerOrderRepository.findByCustomer_Id(customerId);
    }
}
