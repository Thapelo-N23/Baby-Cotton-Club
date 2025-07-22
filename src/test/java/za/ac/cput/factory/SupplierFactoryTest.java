/*
SupplierFactoryTest POJO Class
Author: Phindile Lisa Ngozi
Student Number: 230640893
Date: 2025/05/18
 */
package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class SupplierFactoryTest {

    private static final Supplier supplier = SupplierFactory.createSupplier(
            "SnuggleBabies Clothing Co.",
            "0211234567",
            101
    );

    @Test
    void createSupplier() {
        assertNotNull(supplier);
        System.out.println(supplier);
    }

    @Test
    void createSupplierWithEmptyName() {
        Supplier invalid = SupplierFactory.createSupplier(
                " ",
                "0210836543",
                102
        );
        assertNull(invalid, "Supplier with empty name should be null");
        System.out.println(invalid);
    }

    @Test
    void createSupplierWithNullContact() {
        Supplier invalid = SupplierFactory.createSupplier(
                "Tiny Togs Supplies",
                null,
                103
        );
        assertNull(invalid, "Supplier with null contact should be null");
        System.out.println(invalid);
    }

    @Test
    void createSupplierWithInvalidId() {
        Supplier invalid = SupplierFactory.createSupplier(
                "CuddleWear Baby Co.",
                "0217654221",
                0 // invalid ID
        );
        assertNull(invalid, "Supplier with invalid ID should be null");
        System.out.println(invalid);
    }
}