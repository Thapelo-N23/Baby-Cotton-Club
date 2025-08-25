/*
 * Inventory.java
 * InventoryFactory POJO class
 * Author: O Ntsaluba (230741754)
 * Date: 18 May 2025
 */

package za.ac.cput.factory;

import za.ac.cput.domain.Inventory;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Supplier;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.List;

public class InventoryFactory {

    public static Inventory createInventory(String receivedDate,
                                            String stockAdded,
                                            List<Supplier> supplier,
                                            Product product) {

        // Validate stockAdded
        if (Helper.isNullOrEmpty(stockAdded)) {
            throw new IllegalArgumentException("StockAdded cannot be null or empty");
        }

        // Validate receivedDate
        LocalDate date = Helper.isValidDate(receivedDate);
        if (date == null) {
            throw new IllegalArgumentException("Invalid date format: " + receivedDate);
        }



        return new Inventory.Builder()
                .setReceivedDate(date)
                .setStockAdded(stockAdded)
                .setSupplier(supplier)
                .setProduct(product)
                .build();
    }
}
