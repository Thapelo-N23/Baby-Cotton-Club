package za.ac.cput.service.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Supplier;
import za.ac.cput.factory.ProductFactory;
import za.ac.cput.factory.SupplierFactory;
import za.ac.cput.service.ISupplierService;
import za.ac.cput.service.ProductService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SupplierServiceTest {

    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private ProductService productService;

    private static Supplier supplier;
    private static Product product;

    @Test
    @Order(1)
    void create() {
        product = ProductFactory.createProduct(
                "Hermes",
                "Beige",
                (short) 90,
                "Available",
                null,
                null
        );
        ;
        product = productService.create(product);
        assertNotNull(product, "Product creation failed");
        System.out.println("Created Product: " + product);




        // 3) Create and persist Supplier using the saved Inventory
        supplier = SupplierFactory.createSupplier(
                "SnuggleBabies Clothing Co.",
                "0211234567"

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

    @Test
    @Order(3)
    void createSupplierWithProducts() {
        Product product = ProductFactory.createProduct(
                "SupplierServiceProduct",
                "Red",
                (short) 120,
                "Available",
                null,
                null
        );
        Supplier supplierWithProducts = SupplierFactory.createSupplier(
                "ServiceSupplier",
                "0217777777",
                java.util.Collections.singletonList(product)
        );
        Supplier createdSupplier = supplierService.create(supplierWithProducts);
        assertNotNull(createdSupplier);
        assertNotNull(createdSupplier.getProducts());
        assertEquals(1, createdSupplier.getProducts().size());
        assertEquals("SupplierServiceProduct", createdSupplier.getProducts().get(0).getProductName());
        System.out.println("Created Supplier with Products: " + createdSupplier);
    }
}
