package za.ac.cput.factory;


import za.ac.cput.domain.Discount;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.Date;

public class DiscountFactory {

    public static Discount createDiscount(int discountId, String discountName, String discountType,

                                          String discountValue, String startDate, String endDate) {
        if (Helper.isNullOrEmpty(discountName) || Helper.isNullOrEmpty(discountType) ||
                Helper.isNullOrEmpty(discountValue) ) {
            return null;
        }
      
      if(!Helper.isValidId(discountId)){
        return null;
      }

        LocalDate start = Helper.isValidDate(startDate);
        if (start == null) {
            return null;
        }

        LocalDate end = Helper.isValidDate(endDate);
        if (end == null) {
            return null;
        }

        return new Discount.Builder()
                .setDiscountId(discountId)
                .setDiscountName(discountName)
                .setDiscountType(discountType)
                .setDiscountValue(discountValue)
                .setStartDate(start)
                .setEndDate(end)
                .build();
    }
}