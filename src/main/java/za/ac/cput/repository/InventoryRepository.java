/*
 * InventoryRepository POJO Class
 * Author: Onako Ntsaluba (230741754)
 * Date: 2025/05/25
 */
package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, String> {
}