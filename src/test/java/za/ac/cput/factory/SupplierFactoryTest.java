/*
SupplierFactoryTest POJO Class
Author: Phindile Lisa Ngozi
Student Number: 230640893
Date: 2025/05/18
 */
package za.ac.cput.factory;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.Inventory;
import za.ac.cput.domain.Supplier;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SupplierFactoryTest {

    private static final List<Inventory> inventoryId = Collections.emptyList();

    private static final Supplier supplier = SupplierFactory.createSupplier(
            "SnuggleBabies Clothing Co.",
            "0211234567",
            inventoryId
    );

    @Test
    @Order(1)
    void testCreateSupplier() {
        assertNotNull(supplier);
        assertEquals("SnuggleBabies Clothing Co.", supplier.getSupplierName());
        assertEquals("0211234567", supplier.getContactDetails());
        assertEquals(inventoryId, supplier.getInventoryList());
        System.out.println(supplier);
    }

    @Test
    @Order(2)
    void testCreateSupplierWithEmptyName() {
        Supplier invalid = SupplierFactory.createSupplier(
                " ",
                "0210836543",
                inventoryId
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
                inventoryId
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
        assertNull(invalid, "Supplier with null inventory list should be null");
        System.out.println(invalid);
    }
}
