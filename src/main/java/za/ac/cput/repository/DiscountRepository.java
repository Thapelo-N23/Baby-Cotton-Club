/*
 * DiscountRepository POJO Class
 * Author: Onako Ntsaluba (230741754)
 * Date: 2025/05/25
 */
package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
}