/*
Baby Cotton Club
OrderService
Author: Tsireledzo Netshilonwe
Student Number: 230666426
Date: 2025/05/24
*/

package za.ac.cput.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Order;
import za.ac.cput.repository.OrderRespository;
import za.ac.cput.service.IOrderService;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    private OrderRespository repository;

    @Autowired
    public OrderService(OrderRespository repository) {
        this.repository = repository;
    }
    @Override
    public Order create(Order order) {
        return null;
    }

    @Override
    public Order read(String id) {
        return null;
    }

    @Override
    public Order update(Order order) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }
}
