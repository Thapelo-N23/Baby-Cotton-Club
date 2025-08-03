/*
 * Discount.java
 * DiscountFactory POJO class
 * Author: O Ntsaluba (230741754)
 * Date: 18 May 2025
 */

package za.ac.cput.factory;

import za.ac.cput.domain.Discount;
import za.ac.cput.domain.OrderLine;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class DiscountFactory {
    private static int discountCounter = 1;

    public static Discount createDiscount(String discountName, String discountType,
                                          String discountValue, String startDate, String endDate, OrderLine orderLine) {

        if (Helper.isNullOrEmpty(discountName) || Helper.isNullOrEmpty(discountType) ||
                Helper.isNullOrEmpty(discountValue)) {
            return null;
        }

        LocalDate start = Helper.isValidDate(startDate);
        LocalDate end = Helper.isValidDate(endDate);

        if (start == null || end == null) {
            return null;
        }

        return new Discount.Builder()
                .setDiscountId(discountCounter++)
                .setDiscountName(discountName)
                .setDiscountType(discountType)
                .setDiscountValue(discountValue)
                .setStartDate(start)
                .setEndDate(end)
                .setOrderLine(orderLine)
                .build();
    }
}