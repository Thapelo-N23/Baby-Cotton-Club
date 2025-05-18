/*
 * InventoryFactory POJO Class
 * Author: [Your Name]
 * Student Number: [Your Student Number]
 * Date: 2025/05/18
 */
package za.ac.cput.factory;

import za.ac.cput.domain.Inventory;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class InventoryFactory {

    public static Inventory createInventory(int inventoryId, int productId, String receivedDate,
                                            String stockAdded, int supplierId) {

        if (Helper.isNullOrEmpty(stockAdded)) {
            return null;
        }




        LocalDate date = Helper.isValidDate(receivedDate);
        if (date == null) {
            return null;
        }


        return new Inventory.Builder()
                .setInventoryId(inventoryId)
                .setProductId(productId)
                .setReceivedDate(date)
                .setStockAdded(stockAdded)
                .setSupplierId(supplierId)
                .build();
    }
}
