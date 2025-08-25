/*
 * InventoryController POJO Class
 * Author: Onako Ntsaluba (230741754)
 * Date: 2025/05/25
 */
package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Inventory;
import za.ac.cput.service.impl.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private InventoryService service;

    @Autowired
    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Inventory createInventory(@RequestBody Inventory inventory) {
        return service.create(inventory);
    }

    @GetMapping("/read/{inventoryId}")
    public Inventory readInventory(@PathVariable("inventoryId") Integer inventoryId) {
        return service.read(inventoryId);
    }

    @PutMapping("/update")
    public Inventory updateInventory(@RequestBody Inventory inventory) {
        return service.update(inventory);
    }

    @GetMapping("/getall")
    public List<Inventory> getAllInventories() {
        return service.getAll();
    }
}