/*
 * Baby Cotton Club
 * OrderLineService.java
 * Author: Tsireledzo Netshilonwe
 * Student Number: 230666426
 * Date: 2025/05/24
 */

package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.OrderLine;
import za.ac.cput.repository.OrderLineRepository;
import za.ac.cput.service.IOrderLineService;

import java.util.List;

@Service
public class OrderLineService implements IOrderLineService {

    private final OrderLineRepository repository;

    @Autowired
    public OrderLineService(OrderLineRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrderLine create(OrderLine orderLine) {
        return this.repository.save(orderLine);
    }

    @Override
    public OrderLine read(Integer id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public OrderLine update(OrderLine orderLine) {
        return this.repository.save(orderLine);
    }

    @Override
    public List<OrderLine> getAll() {
        return this.repository.findAll();
    }
}

