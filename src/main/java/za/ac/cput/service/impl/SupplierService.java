
/*
IPaymentService POJO Class
Author: Phindile Lisa Ngozi
Student Number: 230640893
Date: 2025/05/25
 */
package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Supplier;
import za.ac.cput.repository.SupplierRepository;
import za.ac.cput.service.ISupplierService;

import java.util.List;

@Service
public class SupplierService implements ISupplierService {

    private SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Supplier create(Supplier supplier) {
        return this.supplierRepository.save(supplier);
    }

    @Override
    public Supplier read(Integer id) {
        return this.supplierRepository.findById(id).orElse(null);
    }

    @Override
    public Supplier update(Supplier supplier) {
        return this.supplierRepository.save(supplier);
    }

    @Override
    public List<Supplier> getAll() {
        return this.supplierRepository.findAll();
    }
}
