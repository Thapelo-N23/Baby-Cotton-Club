    /*
     * InventoryFactoryTest POJO Class
     * Author: O Ntsaluba (230741754)
     * Date: 18 May 2025
     */
    package za.ac.cput.factory;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Inventory;

import static org.junit.jupiter.api.Assertions.*;

class InventoryFactoryTest {

        @Test
        void testCreateInventorySuccess() {
            Inventory inventory = InventoryFactory.createInventory(
                    201,
                    12,
                    null,
                    "15000",105);
            assertNotNull(inventory);
            assertEquals(201, inventory.getInventoryId());
            System.out.println(inventory);
        }

        @Test
        void testCreateInventoryWithNegativeQuantity() {
            Inventory inventory = InventoryFactory.createInventory(
                    202,
                    102,
                    null,
                    "3000.00",
                    105);

            assertNull(inventory);
        }

        @Test
        void testCreateInventoryWithNegativePrice() {
            Inventory inventory = InventoryFactory.createInventory(
                    203,
                    501,
                    null,
                    "108", 15);

            assertNull(inventory);
        }


        @Test
        void testCreateInventoryWithInvalidId() {
            Inventory inventory = InventoryFactory.createInventory(
                    0,
                    903,
                    null,
                    "105",201);

            assertNull(inventory);
        }

        @Test
        void testCreateInventoryWithNullProductName() {
            Inventory inventory = InventoryFactory.createInventory(
                    205,
                    105,
                    null,
                    "999", 102);


            assertNull(inventory);
        }
    }

