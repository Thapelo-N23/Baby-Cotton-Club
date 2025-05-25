
/*
ISupplierService POJO Class
Author: Phindile Lisa Ngozi
Student Number: 230640893
Date: 2025/05/25
 */
package za.ac.cput.service;

import za.ac.cput.domain.Supplier;
import java.util.List;

public interface ISupplierService extends IService<Supplier, String> {
    boolean delete(String id);

    List<Supplier> getAll();
}

