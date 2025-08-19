package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Inventory;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Supplier;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryFactoryTest {

    @Test
    void createInventory() {
        Product product = ProductFactory.createProduct(
                "Organic Cotton Onesie",
                "White with blue stars",
                (short) 5,
                "Yes",
                null

        );

        Supplier supplier = SupplierFactory.createSupplier(
                "Tiny Tots Clothing Co.",
                "supply@tinytots.com",
                null
        );

        Inventory inventory = InventoryFactory.createInventory(
                "20250810",
                "50 onesies",
                Arrays.asList(supplier),
                product
        );

        assertNotNull(inventory, "Inventory should not be null");
        assertEquals(LocalDate.of(2025, 8, 10), inventory.getReceivedDate());
        assertEquals("50 onesies", inventory.getStockAdded());
        assertEquals(Arrays.asList(supplier), inventory.getSupplier());
        assertEquals(product, inventory.getProduct());

        System.out.println(inventory);
    }

    @Test
    void createInventoryWithSupplier() {
        Product product = ProductFactory.createProduct(
                "Baby Booties",
                "Soft pink with bows",
                (short) 4,
                "Yes",
                null

        );

        Supplier supplier = SupplierFactory.createSupplier(
                "Little Feet Supplies",
                "contact@littlefeet.com",
                null
        );

        Inventory inventory = InventoryFactory.createInventory(
                "20250815",
                "30 pairs of booties",
                Arrays.asList(supplier),
                product
        );

        assertNotNull(inventory, "Inventory should not be null when supplier is valid");
        assertEquals(LocalDate.of(2025, 8, 15), inventory.getReceivedDate());
        assertEquals("30 pairs of booties", inventory.getStockAdded());
        assertEquals(Arrays.asList(supplier), inventory.getSupplier());
        assertEquals(product, inventory.getProduct());

        System.out.println(inventory);
    }

    @Test
    void createInventoryWithEmptyStock() {
        Product product = ProductFactory.createProduct(
                "Organic Cotton Onesie",
                "White with blue stars",
                (short) 5,
                "Yes",
                null
        );

        Supplier supplier = SupplierFactory.createSupplier(
                "Tiny Tots Clothing Co.",
                "supply@tinytots.com",
                null
        );

        Inventory invalid = InventoryFactory.createInventory(
                "20250810",
                "",
                Arrays.asList(supplier),
                product
        );

        assertNull(invalid, "Inventory with empty stock should be null");
    }

    @Test
    void createInventoryWithNullSupplierList() {
        Product product = ProductFactory.createProduct(
                "Organic Cotton Onesie",
                "White with blue stars",
                (short) 5,
                "Yes",
                null
        );

        Inventory invalid = InventoryFactory.createInventory(
                "20250810",
                "20 pairs of booties",
                null,
                product
        );

    }
}