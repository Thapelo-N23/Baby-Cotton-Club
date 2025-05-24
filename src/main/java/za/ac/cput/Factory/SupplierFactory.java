/*
SupplierFactory POJO Class
Author: Phindile Lisa Ngozi
Student Number: 230640893
Date: 2025/05/18
 */
package za.ac.cput.Factory;

import za.ac.cput.domain.Supplier;
import za.ac.cput.util.Helper;

public class SupplierFactory {

    public static Supplier createSupplier(String supplierId, String supplierName, String contactNumber) {
        if (Helper.isNullOrEmpty(supplierId) || Helper.isNullOrEmpty(supplierName) || Helper.isNullOrEmpty(contactNumber)) {
            return null;
        }

        return new Supplier.Builder()
                .setSupplierId(supplierId)
                .setSupplierName(supplierName)
                .setContactNumber(contactNumber)
                .build();
    }
}

