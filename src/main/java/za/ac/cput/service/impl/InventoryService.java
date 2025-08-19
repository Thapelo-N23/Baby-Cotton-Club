/*
InventoryService POJO Class
Author: Onako Ntsaluba
Student Number: 230741754
Date: 2025/05/25
 */

package za.ac.cput.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Inventory;
import za.ac.cput.repository.InventoryRepository;
import za.ac.cput.service.IInventoryService;

import java.util.List;

@Service
public class InventoryService implements IInventoryService {

    private InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Inventory create(Inventory inventory) {
        return this.inventoryRepository.save(inventory);
    }

    @Transactional
    @Override
    public Inventory read(Integer id) {
        Inventory inventory = this.inventoryRepository.findById(id).orElse(null);
        if (inventory != null) {
            inventory.getSupplier().size(); // force initialization of suppliers
        }
        return inventory;
    }

    @Override
    public Inventory update(Inventory inventory) {
        return this.inventoryRepository.save(inventory);
    }

    @Override
    public List<Inventory> getAll() {
        return this.inventoryRepository.findAll();
    }
}
