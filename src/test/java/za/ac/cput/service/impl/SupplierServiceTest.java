package za.ac.cput.service.impl;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Inventory;
import za.ac.cput.domain.Supplier;
import za.ac.cput.factory.InventoryFactory;
import za.ac.cput.factory.ProductFactory;
import za.ac.cput.factory.SupplierFactory;
import za.ac.cput.service.ISupplierService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SupplierServiceTest {

    @Autowired
    private ISupplierService service;

    private static Inventory inventory;
    private static Supplier supplier;

    @Test
    @Order(1)
    void create() {

        var product = ProductFactory.createProduct(
                "Baby Blanket", "Pink", (short) 199, "true"
        );

        inventory = InventoryFactory.createInventory(
                "2025-08-03",
                "100 units",
                Collections.emptyList(),
                product
        );
        assertNotNull(inventory);

        supplier = SupplierFactory.createSupplier(
                "SnuggleBabies Clothing Co.",
                "0211234567",
                inventory
        );
        assertNotNull(supplier);

        Supplier created = service.create(supplier);
        assertNotNull(created);
        supplier = created;
        System.out.println("Created Supplier: " + supplier);
    }

    @Transactional
    @Test
    @Order(2)
    void read() {
        Supplier read = service.read(String.valueOf(supplier.getSupplierId()));
        assertNotNull(read);
        System.out.println("Read Supplier: " + read);
    }

    @Test
    @Order(3)
    void update() {
        Supplier updated = new Supplier.Builder()
                .copy(supplier)
                .setContactDetails("0217654321")
                .build();

        Supplier result = service.update(updated);
        assertNotNull(result);
        supplier = result;
        System.out.println("Updated Supplier: " + result);
    }

    @Test
    @Order(4)
    void getAll() {
        assertNotNull(service.getAll());
        System.out.println("All Suppliers: " + service.getAll());
    }
}
