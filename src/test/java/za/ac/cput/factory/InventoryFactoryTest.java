/*
 * InventoryFactoryTest POJO Class
 * Author: O Ntsaluba (230741754)
 * Date: 30 July 2025
 */
package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class InventoryFactoryTest {

    private static final Product testProduct = ProductFactory.createProduct(
            78659L,"Organic Cotton Onesie",
            "White with blue stars",
            (short) 5,
            "Yes"
    );

    private static final Supplier testSupplier = SupplierFactory.createSupplier(
            "Tiny Tots Clothing Co.",
            "supply@tinytots.com",
             null
    );

    private static final List<Supplier> testSuppliers = List.of(testSupplier);

    private static final Inventory testInventory = InventoryFactory.createInventory(
            LocalDate.now().toString(),
            "50 onesies",
            testSuppliers,
            testProduct
    );

    @Test
    void createInventory() {
        assertNotNull(testInventory);
        System.out.println(testInventory);
    }

    @Test
    void createInventoryWithEmptyStock() {
        Inventory invalid = InventoryFactory.createInventory(
                LocalDate.now().toString(),
                "",
                testSuppliers,
                testProduct
        );
        assertNull(invalid, "Inventory with empty stock should be null");
        System.out.println(invalid);
    }

    @Test
    void createInventoryWithNullSupplier() {
        Inventory invalid = InventoryFactory.createInventory(
                LocalDate.now().toString(),
                "20 pairs of booties",
                null,
                testProduct
        );
        assertNull(invalid, "Inventory with null supplier should be null");
        System.out.println(invalid);
    }

    @Test
    void createInventoryWithInvalidDate() {
        Inventory invalid = InventoryFactory.createInventory(
                "invalid-date",
                "10 sun hats",
                testSuppliers,
                testProduct
        );
        assertNull(invalid, "Inventory with invalid date should be null");
        System.out.println(invalid);
    }

    @Test
    void createInventoryWithNullProduct() {
        Inventory invalid = InventoryFactory.createInventory(
                LocalDate.now().toString(),
                "15 bibs",
                testSuppliers,
                null
        );
        assertNull(invalid, "Inventory with null product should be null");
        System.out.println(invalid);
    }
}