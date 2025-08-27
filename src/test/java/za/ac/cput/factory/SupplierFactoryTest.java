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
            "20250731",
            "100 units",
            Collections.emptyList(),
            testProduct
    );

    private static final Supplier supplier1 = SupplierFactory.createSupplier(
            "SnuggleBabies Clothing Co.",
            "0211234567",
            testInventory
    );

    private static final Supplier supplier2 = SupplierFactory.createSupplier(
            "Tiny Togs Supplies",
            "0210987654",
            testInventory
    );

    @Test
    @Order(1)
    void createSupplier() {
        assertNotNull(supplier1);
        System.out.println(supplier1);
    }

    @Test
    @Order(2)
    void createAnotherSupplier() {
        assertNotNull(supplier2);
        System.out.println(supplier2);
    }

    @Test
    @Order(3)
    void testCreateSupplierWithNullName() {
        Supplier invalid = SupplierFactory.createSupplier(
                null,
                "0210836543",
                testInventory
        );
        assertNull(invalid, "Supplier with null name should be null");
        System.out.println(invalid);
    }

    @Test
    @Order(4)
    void testCreateSupplierWithNullContact() {
        Supplier invalid = SupplierFactory.createSupplier(
                "CuddleWear Baby Co.",
                null,
                testInventory
        );
        assertNull(invalid, "Supplier with null contact should be null");
        System.out.println(invalid);
    }

    @Test
    @Order(5)
    void testCreateSupplierWithNullInventory() {
        Supplier invalid = SupplierFactory.createSupplier(
                "Baby Boutique",
                "0217654221",
                null
        );
        assertNull(null, "Supplier with null inventory should be null");
        System.out.println(invalid);
    }
}