/*
 * DiscountService POJO Class
 * Author: Onako Ntsaluba (230741754)
 * Date: 2025/05/25
 */

package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Discount;
import za.ac.cput.repository.DiscountRepository;
import za.ac.cput.service.IDiscountService;

import java.util.List;

@Service
public class DiscountService implements IDiscountService {
    private DiscountRepository repository;

    @Autowired
    public DiscountService(DiscountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Discount create(Discount discount) {
        return repository.save(discount);
    }

    @Override
    public Discount read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Discount update(Discount discount) {
        return repository.save(discount);
    }

    @Override
    public boolean delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Discount> getAll() {
        return repository.findAll();
    }
}