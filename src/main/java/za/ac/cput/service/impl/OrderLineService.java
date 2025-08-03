/*
Baby Cotton Club
OrderLineService
Author: Tsireledzo Netshilonwe
Student Number: 230666426
Date: 2025/05/24
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

    private OrderLineRepository repository;

    @Autowired
    public OrderLineService(OrderLineRepository repository) {

        this.repository = repository;
    }

    @Override
    public OrderLine create(OrderLine orderLine) {

        return null;
    }

    @Override
    public OrderLine read(String id) {

        return null;
    }

    @Override
    public OrderLine update(OrderLine orderLine) {

        return null;
    }

    @Override
    public List<OrderLine> getAll() {

        return null;
    }
}
