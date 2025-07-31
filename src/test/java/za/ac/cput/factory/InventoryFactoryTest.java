/*
 * InventoryFactoryTest.java
 * InventoryFactoryTest POJO class
 * Author: O Ntsaluba (230741754)
 * Date: 30 July 2025
 */

package za.ac.cput.factory;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import za.ac.cput.domain.Inventory;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Supplier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InventoryFactoryTest {

    private static List<Supplier> suppliers = new ArrayList<>();
    private static Inventory inventory1 = InventoryFactory.createInventory(
            1001,
            LocalDate.now().toString(),
            "50 units",
            2001
    );
    private static Product product1 = ProductFactory.createProduct("Laptop", "Dell XPS 15", (short) 5, "Yes");
    private static Supplier supplier1 = SupplierFactory.createSupplier("Tech Distributors", "tech@dist.com", 101);

    @Test
    @Order(1)
    public void testCreateInventory() {
        assertNotNull(inventory1);
        System.out.println(inventory1);
    }

    @Test
    @Order(2)
    public void testCreateProduct() {
        assertNotNull(product1);
        System.out.println(product1);
    }

    @Test
    @Order(3)
    public void testCreateSupplier() {
        assertNotNull(supplier1);
        System.out.println(supplier1);
    }

    @Test
    @Order(4)
    void testCreateInventoryWithRelations() {
        suppliers.add(supplier1);
        Inventory inventoryWithRelations = InventoryFactory.createInventory(
                1002,
                LocalDate.now().toString(),
                "20 units",
                2002
        );
        assertNotNull(inventoryWithRelations);
        assertEquals(1002, inventoryWithRelations.getProductId());
        System.out.println("Inventory with Relations: " + inventoryWithRelations);
    }

    @Test
    @Order(5)
    void testCreateInventoryWithEmptyStock() {
        Inventory inventory = InventoryFactory.createInventory(
                1003,
                LocalDate.now().toString(),
                "",
                2003
        );
        assertNull(inventory);
        System.out.println("Test for empty stock passed");
    }
}