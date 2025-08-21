
package za.ac.cput.service.impl;

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

@SpringBootTest(classes =Main.class)
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

        Supplier supplier = SupplierFactory.createSupplier(
                "SnuggleBabies Clothing Co.",
                "0211234580",
                inventory
        );

        // Create Product
        product = ProductFactory.createProduct("Nike","Blue",
                (short)67,"Yes",null);

        // Create Inventory
        inventory = InventoryFactory.createInventory(
                "20250816",
                "30 onesies",
                null,
                product
        );

        System.out.println("Created Inventory: " + inventory);
    }

    @Test
    @Order(2)
    void read() {
        List<Inventory> allInventories = service.getAll();
        assertFalse(allInventories.isEmpty(), "No inventories found in DB");

        Inventory existing = allInventories.get(0);
        Inventory read = service.read(existing.getInventoryId());

        assertNotNull(read);
        assertEquals(existing.getInventoryId(), read.getInventoryId());

        System.out.println("Read Inventory: " + read); }

    @Test
    @Order(3)
    void update() {
        Inventory updatedInventory = new Inventory.Builder()
                .copy(inventory)
                .setStockAdded("30 onesies")
                .build();


        Inventory updated = service.update(updatedInventory);
        assertNotNull(updated);
        System.out.println("Updated Inventory: " + updated);
    }

    @Test
    @Order(4)
    void getAll() {
        assertNotNull(service.getAll());
        System.out.println("All Inventories: " + inventory);
    }
}