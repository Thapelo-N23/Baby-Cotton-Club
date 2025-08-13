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

public class SupplierFactory {

    public static Supplier createSupplier(String supplierName,
                                          String contactDetails,
                                          Inventory inventory) {
        if (Helper.isNullOrEmpty(supplierName) || Helper.isNullOrEmpty(contactDetails) || inventory == null) {
            return null;
        }

        return new Supplier.Builder()
                .setSupplierName(supplierName)
                .setContactDetails(contactDetails)
                .setInventory(inventory)
                .build();
    }
}



