/*
package za.ac.cput.service.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Inventory;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Supplier;
import za.ac.cput.factory.InventoryFactory;
import za.ac.cput.factory.ProductFactory;
import za.ac.cput.factory.SupplierFactory;
import za.ac.cput.service.IInventoryService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InventoryServiceTest {

    @Autowired
    private IInventoryService service;

    private static Product product;
    private static Supplier supplier;
    private static Inventory inventory;

    @BeforeAll
    static void setup() {
        product = ProductFactory.createProduct(
                90099L,
                "Lancewood",
                "Yellow",
                new Short(String.valueOf(50)),
                "OUT OF STOCK"
        );

        supplier = SupplierFactory.createSupplier(
                "Tiny Tots",
                "supply@tinytots.com",
                null
        );
    }

    @Test
    @Order(1)
    void testCreateInventoryWithoutRelations() {
        inventory = InventoryFactory.createInventory(
                LocalDate.now().toString(),
                "100 units",
                null,
                product
        );

        Inventory created = service.create(inventory);
        assertNotNull(created);
        inventory = created;
        System.out.println("Created Inventory (No Suppliers): " + inventory);
    }

    @Test
    @Order(2)
    void testReadInventory() {
        Inventory found = service.read(inventory.getInventoryId());
        assertNotNull(found);
        System.out.println("Read Inventory: " + found);
    }

    @Test
    @Order(3)
    void testUpdateInventory() {
        Inventory updated = new Inventory.Builder()
                .copy(inventory)
                .setStockAdded("150 units")
                .build();

        Inventory result = service.update(updated);
        assertNotNull(result);
        inventory = result;
        System.out.println("Updated Inventory: " + result);
    }

    @Test
    @Order(4)
    void testCreateInventoryWithRelations() {
        Inventory inventoryWithRelations = InventoryFactory.createInventory(
                LocalDate.now().toString(),
                "200 units",
                List.of(supplier),
                product
        );

        Inventory created = service.create(inventoryWithRelations);
        assertNotNull(created);
        System.out.println("Created Inventory (With Supplier): " + created);
    }

    @Test
    @Order(5)
    void testGetAllInventories() {
        List<Inventory> inventories = service.getAll();
        assertNotNull(inventories);
        assertFalse(inventories.isEmpty());
        System.out.println("Total Inventories: " + inventories.size());
    }
}
 */
