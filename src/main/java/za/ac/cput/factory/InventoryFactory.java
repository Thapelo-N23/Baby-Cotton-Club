/*
 * Inventory.java
 * InventoryFactory POJO class
 * Author: O Ntsaluba (230741754)
 * Date: 18 May 2025
 */

package za.ac.cput.factory;

import za.ac.cput.domain.Inventory;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class InventoryFactory {
    private static int inventoryCounter = 1;

    public static Inventory createInventory(int productId, String receivedDate,
                                            String stockAdded, int supplierId) {
        if (Helper.isNullOrEmpty(stockAdded)) {
            return null;
        }

        LocalDate date = Helper.isValidDate(receivedDate);
        if (date == null) {
            return null;
        }

        return new Inventory.Builder()
                .setInventoryId(inventoryCounter++)
                .setProductId(productId)
                .setReceivedDate(date)
                .setStockAdded(stockAdded)
                .setSupplierId(supplierId)
                .build();
    }
}