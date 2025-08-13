package za.ac.cput.service.impl;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Inventory;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Supplier;
import za.ac.cput.factory.InventoryFactory;
import za.ac.cput.factory.ProductFactory;
import za.ac.cput.factory.SupplierFactory;
import za.ac.cput.service.ISupplierService;
import za.ac.cput.service.IInventoryService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Ensures same instance for all tests
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SupplierServiceTest {

    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private IInventoryService inventoryService;

    private Inventory inventory;
    private Supplier supplier;

    @Test
    @Order(1)
    void create() {
        Product product = ProductFactory.createProduct(
                "Baby Blanket", "Pink", (short) 199, "true"
        );
        assertNotNull(product, "Product creation failed");

        inventory = InventoryFactory.createInventory(
                "20250803",
                "100 units",
                Collections.emptyList(),
                product
        );
        assertNotNull(inventory, "Inventory creation failed");

        Inventory savedInventory = inventoryService.create(inventory);
        assertNotNull(savedInventory, "Saving inventory failed");

        supplier = SupplierFactory.createSupplier(
                "SnuggleBabies Clothing Co.",
                "0211234567",
                savedInventory
        );
        assertNotNull(supplier, "SupplierFactory returned null");

        Supplier created = supplierService.create(supplier);
        assertNotNull(created, "Saving supplier failed");

        supplier = created;
        System.out.println("Created Supplier: " + supplier);
    }

    @Transactional
    @Test
    @Order(2)
    void read() {
        assertNotNull(supplier, "Supplier is null before read");
        Supplier read = supplierService.read(supplier.getSupplierId());
        assertNotNull(read, "Supplier read from DB is null");
        System.out.println("Read Supplier: " + read);
    }

    @Test
    @Order(3)
    void update() {
        // Fallback: fetch supplier from DB if null
        if (supplier == null) {
            supplier = supplierService.getAll().stream().findFirst().orElse(null);
        }
        assertNotNull(supplier, "Supplier should exist before update");

        Supplier updated = new Supplier.Builder()
                .copy(supplier)
                .setContactDetails("0217654321")
                .build();

        Supplier result = supplierService.update(updated);
        assertNotNull(result, "Updating supplier failed");

        supplier = result;
        System.out.println("Updated Supplier: " + result);
    }

    @Test
    @Order(4)
    void getAll() {
        assertNotNull(supplierService.getAll(), "getAll() returned null");
        System.out.println("All Suppliers: " + supplierService.getAll());
    }
}
