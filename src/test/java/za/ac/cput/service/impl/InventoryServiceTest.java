package za.ac.cput.service.impl;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.Main;
import za.ac.cput.domain.Inventory;
import za.ac.cput.domain.Supplier;
import za.ac.cput.domain.Product;
import za.ac.cput.factory.InventoryFactory;
import za.ac.cput.factory.SupplierFactory;
import za.ac.cput.factory.ProductFactory;
import za.ac.cput.service.IInventoryService;
import za.ac.cput.service.ISupplierService;
import za.ac.cput.service.ProductService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Main.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InventoryServiceTest {

    @Autowired
    private IInventoryService service;

    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private ProductService productService;

    private static Supplier supplier;
    private static Product product;
    private static Inventory inventory;

    @Test
    @Order(1)
    void create() {
        // Create and persist Product
        product = ProductFactory.createProduct("Nike", "Blue", (short) 67, "Yes", null);
        product = productService.create(product);

        // Create and persist Inventory
        inventory = InventoryFactory.createInventory("20250816", "30 onesies", null, product);
        inventory = service.create(inventory);

        // Create and persist Supplier
        supplier = SupplierFactory.createSupplier("SnuggleBabies Clothing Co.", "0211234580", inventory);
        supplier = supplierService.create(supplier);

        assertNotNull(inventory.getInventoryId());
        System.out.println("Created Inventory: " + inventory);
    }

    @Test
    @Order(2)
    @Transactional
    void read() {
        Inventory read = service.read(inventory.getInventoryId());
        assertNotNull(read);
        assertEquals(inventory.getInventoryId(), read.getInventoryId());

        // within transaction, lazy product can be accessed safely
        System.out.println("Read Inventory: " + read.getProduct());
    }


    @Test
    @Order(3)
    void update() {
        assertNotNull(inventory, "Inventory should exist from create test");

        Inventory updatedInventory = new Inventory.Builder()
                .copy(inventory)
                .setStockAdded("50 onesies") // changed value
                .build();

        Inventory updated = service.update(updatedInventory);

        assertNotNull(updated);
        assertEquals("50 onesies", updated.getStockAdded(), "StockAdded should be updated");

        // refresh static inventory reference
        inventory = updated;

        System.out.println("Updated Inventory: " + updated);
    }

    @Test
    @Order(4)
    void getAll() {
        List<Inventory> allInventories = service.getAll();

        assertNotNull(allInventories);
        assertFalse(allInventories.isEmpty(), "Inventory list should not be empty");

        System.out.println(" All Inventories: " + allInventories);
    }
}
