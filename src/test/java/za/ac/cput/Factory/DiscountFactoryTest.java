/*
 * DiscountFactoryTest POJO Class
 * Author: O Ntsaluba (230741754)
 * Date: 18 May 2025
 */
package za.ac.cput.Factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Discount;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DiscountFactoryTest {

    @Test
    void testCreateDiscountSuccess() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(7);

        Discount discount = DiscountFactory.createDiscount(
                101,
                "Winter Sale",
                "Percentage",
                "20%",
                startDate,
                endDate
        );

        assertNotNull(discount);
        assertEquals(101, discount.getDiscountId());
        assertEquals("Winter Sale", discount.getDiscountName());
        assertEquals("Percentage", discount.getDiscountType());
        assertEquals("20%", discount.getDiscountValue());
        assertEquals(startDate, discount.getStartDate());
        assertEquals(endDate, discount.getEndDate());
        System.out.println(discount);
    }

    @Test
    void testCreateDiscountWithEmptyName() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(5);

        Discount discount = DiscountFactory.createDiscount(
                103,
                "",
                "Fixed",
                "R50 off",
                startDate,
                endDate
        );

        assertNull(discount);
    }

    @Test
    void testCreateDiscountWithNegativeId() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(3);

        Discount discount = DiscountFactory.createDiscount(
                -1,
                "Negative ID",
                "Fixed",
                "R20 off",
                startDate,
                endDate
        );

        assertNull(discount);
    }

    @Test
    void testCreateDiscountWithInvalidDates() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.minusDays(3);

        Discount discount = DiscountFactory.createDiscount(
                102,
                "Expired Deal",
                "Fixed",
                "R30 off",
                startDate,
                endDate
        );

        assertNull(discount);
    }

    @Test
    void testCreateDiscountWithNullDates() {
        Discount discount = DiscountFactory.createDiscount(
                104,
                "Null Dates",
                "Percentage",
                "10%",
                null,
                null
        );

        assertNull(discount);
    }
}