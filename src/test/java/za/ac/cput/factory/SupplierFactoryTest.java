/*
SupplierFactoryTest POJO Class
Author: Phindile Lisa Ngozi
Student Number: 230640893
Date: 2025/05/18
 */
package za.ac.cput.factory;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.Inventory;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Supplier;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SupplierFactoryTest {

    private static final Product testProduct = new Product();

    private static final Inventory testInventory = InventoryFactory.createInventory(
            "2025-07-31",
            "100 units",
            Collections.emptyList(),
            testProduct
    );

    private static final Supplier supplier = SupplierFactory.createSupplier(
            "SnuggleBabies Clothing Co.",
            "0211234567",
            testInventory
    );

    @Test
    @Order(1)
    void testCreateSupplier() {
        assertNotNull(supplier);
        assertEquals("SnuggleBabies Clothing Co.", supplier.getSupplierName());
        assertEquals("0211234567", supplier.getContactDetails());
        assertNotNull(supplier.getInventory());
        assertEquals(testInventory.getInventoryId(), supplier.getInventory().getInventoryId());
        System.out.println(supplier);
    }

    @Test
    @Order(2)
    void testCreateSupplierWithEmptyName() {
        Supplier invalid = SupplierFactory.createSupplier(
                " ",
                "0210836543",
                testInventory
        );
        assertNull(invalid, "Supplier with empty name should be null");
        System.out.println(invalid);
    }

    @Test
    @Order(3)
    void testCreateSupplierWithNullContact() {
        Supplier invalid = SupplierFactory.createSupplier(
                "Tiny Togs Supplies",
                null,
                testInventory
        );
        assertNull(invalid, "Supplier with null contact should be null");
        System.out.println(invalid);
    }

    @Test
    @Order(4)
    void testCreateSupplierWithNullInventory() {
        Supplier invalid = SupplierFactory.createSupplier(
                "CuddleWear Baby Co.",
                "0217654221",
                null
        );
        assertNull(invalid, "Supplier with null inventory should be null");
        System.out.println(invalid);
    }
}
