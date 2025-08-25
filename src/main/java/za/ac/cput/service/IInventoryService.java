/*
 * IInventoryService POJO Class
 * Author: Onako Ntsaluba (230741754)
 * Date: 2025/05/25
 */
package za.ac.cput.service;

import za.ac.cput.domain.Inventory;
import java.util.List;

public interface IInventoryService extends IService<Inventory, Integer> {

    List<Inventory> getAll();
}