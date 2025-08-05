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
import za.ac.cput.domain.Order;
import za.ac.cput.repository.OrderRepository;
import za.ac.cput.service.IOrderService;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order create(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public Order read(Integer id) {
        return this.orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order update(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public List<Order> getAll() {
        return this.orderRepository.findAll();
    }
}
