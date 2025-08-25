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
    private ProductService productService;

    private static Supplier supplier;
    private static Inventory inventory;
    private static Product product;

    @Test
    @Order(1)
    void create() {
        product = ProductFactory.createProduct(
                "Hermes",
                "Beige",
                (short) 90,
                "Available",
                null
        );
        ;
        product = productService.create(product);
        assertNotNull(product, "Product creation failed");
        System.out.println("Created Product: " + product);


        // 2) Create and persist Inventory using the saved Product
        inventory = InventoryFactory.createInventory(
                "20250803",
                "100 units",
                Collections.emptyList(),
                product

                );
        inventory = inventoryService.create(inventory);
        assertNotNull(inventory, "Inventory creation failed");
        System.out.println("Created Inventory: " + inventory);

        // 3) Create and persist Supplier using the saved Inventory
        supplier = SupplierFactory.createSupplier(
                "SnuggleBabies Clothing Co.",
                "0211234567",
                inventory
        );
        supplier = supplierService.create(supplier);
        assertNotNull(supplier, "Supplier creation failed");
        System.out.println("Created Supplier: " + supplier);
    }

    @Test
    @Order(2)
    void read() {
        Supplier readSupplier = supplierService.read(supplier.getSupplierId());
        assertNotNull(readSupplier, "Supplier must exist");
        System.out.println("Read Supplier: " + readSupplier);
    }

    @Test
    @Order(3)
    void update() {
        Supplier updatedSupplier = new Supplier.Builder()
                .copy(supplier)
                .setContactDetails("0217654321")
                .setInventory(inventory)
                .build();

        Supplier saved = supplierService.update(updatedSupplier);
        assertNotNull(saved, "Supplier update failed");
        System.out.println("Updated Supplier: " + saved);
        supplier = saved;
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
