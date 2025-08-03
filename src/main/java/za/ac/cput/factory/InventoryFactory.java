/*
 * Inventory.java
 * InventoryFactory POJO class
 * Author: O Ntsaluba (230741754)
 * Date: 18 May 2025
 */

package za.ac.cput.factory;

import za.ac.cput.domain.*;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.List;

public class InventoryFactory {
    private static int inventoryCounter = 1;

    public static Inventory createInventory(String receivedDate, String stockAdded, List<Supplier> supplier, Product product) {
        if (Helper.isNullOrEmpty(stockAdded)) {
            return null;
        }

        LocalDate date = Helper.isValidDate(receivedDate);
        if (date == null) {
            return null;
        }

        if (supplier == null) {
            return null;
        }

        return new Inventory.Builder()
                .setInventoryId(inventoryCounter++)
                .setReceivedDate(date)
                .setStockAdded(stockAdded)
                .setSupplier(supplier)
                .setProduct(product)
                .build();
    }
}