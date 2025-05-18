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

    @Test
    void testCreateSupplierSuccess() {
        Supplier supplier = SupplierFactory.createSupplier("SUP001", "SnuggleBabies Clothing Co.", "0211234567");
        assertNotNull(supplier);
        assertEquals("SUP001", supplier.getSupplierId());
        assertEquals("SnuggleBabies Clothing Co.", supplier.getSupplierName());
        assertEquals("0211234567", supplier.getContactNumber());
        System.out.println(supplier);
    }

    @Test
    void testCreateSupplierWithEmptyName() {
        Supplier supplier = SupplierFactory.createSupplier("SUP002", "", "0210836543");
        assertNull(supplier);
    }

    @Test
    void testCreateSupplierWithNullContact() {
        Supplier supplier = SupplierFactory.createSupplier("SUP003", "Tiny Togs Supplies", null);
        assertNull(supplier);
    }

    @Test
    void testCreateSupplierWithEmptyId() {
        Supplier supplier = SupplierFactory.createSupplier("", "CuddleWear Baby Co.", "0217654221");
        assertNull(supplier);
    }
}
