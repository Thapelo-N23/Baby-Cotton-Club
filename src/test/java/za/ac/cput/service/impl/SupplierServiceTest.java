package za.ac.cput.service.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Inventory;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Supplier;
import za.ac.cput.factory.InventoryFactory;
import za.ac.cput.factory.ProductFactory;
import za.ac.cput.factory.SupplierFactory;
import za.ac.cput.service.IInventoryService;
import za.ac.cput.service.ISupplierService;
import za.ac.cput.service.ProductService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SupplierServiceTest {

    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private IInventoryService inventoryService;

    @Autowired
    private ProductService productService; // <-- persist Product first

    private static Supplier supplier;
    private static Inventory inventory;
    private static Product product;

    @Test
    @Order(1)
    void create() {
        // 1) Create & save Product FIRST
        product = ProductFactory.createProduct(
                "Baby Blanket",
                "Pink",
                (short) 199,
                "true"
        );
        assertNotNull(product, "ProductFactory returned null");
        product = productService.create(product);
        assertNotNull(product.getProductId(), "Saved Product ID should not be null");

        // 2) Create & save Inventory linked to saved Product
        inventory = InventoryFactory.createInventory(
                "20250803",
                "100 units",
                Collections.emptyList(),
                product
        );
        assertNotNull(inventory, "InventoryFactory returned null");
        inventory = inventoryService.create(inventory);
        assertNotNull(inventory.getInventoryId(), "Saved Inventory ID should not be null");

        // 3) Create & save Supplier linked to saved Inventory
        supplier = SupplierFactory.createSupplier(
                "SnuggleBabies Clothing Co.",
                "0211234567",
                inventory
        );
        assertNotNull(supplier, "SupplierFactory returned null");

        Supplier created = supplierService.create(supplier);
        assertNotNull(created, "Saving supplier failed");
        assertNotNull(created.getSupplierId(), "Supplier ID should not be null");

        supplier = created; // keep for later tests
        System.out.println("Created Supplier: " + supplier);
    }

    @Test
    @Order(2)
    void read() {
        assertNotNull(supplier, "Supplier must be created before reading");
        Supplier read = supplierService.read(supplier.getSupplierId());
        assertNotNull(read, "Read supplier is null");
        assertEquals(supplier.getSupplierId(), read.getSupplierId());
        System.out.println("Read Supplier: " + read);
    }

    @Test
    @Order(3)
    void update() {
        assertNotNull(supplier, "Supplier must be created before updating");

        Supplier updated = new Supplier.Builder()
                .copy(supplier)
                .setContactDetails("0217654321")
                .build();

        Supplier saved = supplierService.update(updated);
        assertNotNull(saved, "Updated supplier is null");
        assertEquals("0217654321", saved.getContactDetails());
        supplier = saved;

        System.out.println("Updated Supplier: " + supplier);
    }

    @Test
    @Order(4)
    void getAll() {
        List<Supplier> all = supplierService.getAll();
        assertNotNull(all, "getAll() returned null");
        assertFalse(all.isEmpty(), "getAll() should not be empty");
        System.out.println("All Suppliers: " + all);
    }
}