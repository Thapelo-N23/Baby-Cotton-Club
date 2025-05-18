package za.ac.cput.factory;


import za.ac.cput.domain.Discount;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.Date;

public class DiscountFactory {

    public static Discount createDiscount(int discountId, String discountName, String discountType,
                                          String discountValue, LocalDate date) {
        if (Helper.isNullOrEmpty(discountName) || Helper.isNullOrEmpty(discountType) ||
                Helper.isNullOrEmpty(discountValue) || date == null ) {
            return null;
        }

        if (!Helper.isValidId(discountId)) {
            return null;
        }


        return new Discount.Builder()
                .setDiscountId(discountId)
                .setDiscountName(discountName)
                .setDiscountType(discountType)
                .setDiscountValue(discountValue)
                .setLocalDate(date)

                .build();
    }
}