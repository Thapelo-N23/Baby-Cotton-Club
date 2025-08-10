package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Inventory;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Supplier;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryFactoryTest {

    @Test
    void createValidInventory() {
        Product product = ProductFactory.createProduct(
                "Organic Cotton Onesie",
                "White with blue stars",
                (short) 5,
                "Yes"
        );

        Supplier supplier = SupplierFactory.createSupplier(
                "Tiny Tots Clothing Co.",
                "supply@tinytots.com",
                null
        );

        Inventory inventory = InventoryFactory.createInventory(
                LocalDate.now().toString(),
                "50 onesies",
                List.of(supplier),
                product
        );

        assertNotNull(inventory, "Valid inventory should not be null");
        assertEquals("50 onesies", inventory.getStockAdded());
        assertEquals(product, inventory.getProduct());
        assertEquals(List.of(supplier), inventory.getSupplier());
        assertNotNull(inventory.getReceivedDate());
        assertTrue(inventory.getInventoryId() > 0);

        System.out.println(inventory);
    }

    @Test
    void createInventoryWithEmptyStock() {
        Product product = ProductFactory.createProduct(
                "Organic Cotton Onesie",
                "White with blue stars",
                (short) 5,
                "Yes"
        );

        Supplier supplier = SupplierFactory.createSupplier(
                "Tiny Tots Clothing Co.",
                "supply@tinytots.com",
                null
        );

        Inventory invalid = InventoryFactory.createInventory(
                LocalDate.now().toString(),
                "",
                List.of(supplier),
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
                "Yes"
        );

        Inventory invalid = InventoryFactory.createInventory(
                LocalDate.now().toString(),
                "20 pairs of booties",
                null,
                product
        );

        assertNull(invalid, "Inventory with null supplier list should be null");
    }

    @Test
    void createInventoryWithInvalidDate() {
        Product product = ProductFactory.createProduct(
                "Organic Cotton Onesie",
                "White with blue stars",
                (short) 5,
                "Yes"
        );

        Supplier supplier = SupplierFactory.createSupplier(
                "Tiny Tots Clothing Co.",
                "supply@tinytots.com",
                null
        );

        Inventory invalid = InventoryFactory.createInventory(
                "invalid-date",
                "10 sun hats",
                List.of(supplier),
                product
        );

        assertNull(invalid, "Inventory with invalid date should be null");
    }

    @Test
    void createInventoryWithNullProduct() {
        Supplier supplier = SupplierFactory.createSupplier(
                "Tiny Tots Clothing Co.",
                "supply@tinytots.com",
                null
        );

        Inventory inventory = InventoryFactory.createInventory(
                LocalDate.now().toString(),
                "15 bibs",
                List.of(supplier),
                null
        );
        assertNotNull(inventory, "Inventory with null product will still be created unless validated");
        assertNull(inventory.getProduct());
    }
}
