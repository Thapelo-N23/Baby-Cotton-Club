/*
 * IDiscountService POJO Class
 * Author: Onako Ntsaluba (230741754)
 * Date: 2025/05/25
 */
package za.ac.cput.service;

import za.ac.cput.domain.Discount;
import java.util.List;

public interface IDiscountService extends IService<Discount, Integer> {
    List<Discount> getAll();
}