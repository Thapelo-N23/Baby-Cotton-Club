/*
SupplierFactory POJO Class
Author: Phindile Lisa Ngozi
Student Number: 230640893
Date: 2025/05/18
 */
package za.ac.cput.factory;

import za.ac.cput.domain.Inventory;
import za.ac.cput.domain.Supplier;
import za.ac.cput.util.Helper;

import java.util.List;

public class SupplierFactory {

    private static int supplierCounter = 1;

    public static Supplier createSupplier(String supplierName,
                                          String contactDetails,
                                          List<Inventory> inventoryId) {
        if (Helper.isNullOrEmpty(supplierName) || Helper.isNullOrEmpty(contactDetails) || inventoryId == null) {
            return null;
        }

        return new Supplier.Builder()
                .setSupplierId(supplierCounter++)
                .setSupplierName(supplierName)
                .setContactDetails(contactDetails)
                .setInventoryId(inventoryId)
                .build();
    }
}

