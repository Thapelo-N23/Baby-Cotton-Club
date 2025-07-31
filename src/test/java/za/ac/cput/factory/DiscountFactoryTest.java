/*
 * DiscountFactoryTest POJO Class
 * Author: O Ntsaluba (230741754)
 * Date: 30 July 2025
 */

package za.ac.cput.factory;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import za.ac.cput.domain.Discount;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DiscountFactoryTest {

    private static Discount discount1 = DiscountFactory.createDiscount(
            "Winter Sale",
            "Percentage",
            "20%",
            LocalDate.now().toString(),
            LocalDate.now().plusDays(7).toString()
    );

    private static Discount discount2 = DiscountFactory.createDiscount(
            "Summer Special",
            "Fixed",
            "R50 off",
            LocalDate.now().toString(),
            LocalDate.now().plusDays(14).toString()
    );

    @Test
    @Order(1)
    public void testCreateDiscount() {
        assertNotNull(discount1);
        assertEquals("Winter Sale", discount1.getDiscountName());
        assertEquals("Percentage", discount1.getDiscountType());
        System.out.println("Discount 1: " + discount1);
    }

    @Test
    @Order(2)
    public void testCreateSecondDiscount() {
        assertNotNull(discount2);
        assertEquals("Summer Special", discount2.getDiscountName());
        assertEquals("Fixed", discount2.getDiscountType());
        System.out.println("Discount 2: " + discount2);
    }

    @Test
    @Order(3)
    void testCreateDiscountWithEmptyName() {
        Discount discount = DiscountFactory.createDiscount(
                "",
                "Fixed",
                "R50 off",
                LocalDate.now().toString(),
                LocalDate.now().plusDays(5).toString()
        );
        assertNull(discount);
        System.out.println("Test for empty name passed");
    }

    @Test
    @Order(4)
    void testCreateDiscountWithInvalidDates() {
        Discount discount = DiscountFactory.createDiscount(
                "Expired Deal",
                "Fixed",
                "R30 off",
                LocalDate.now().plusDays(3).toString(),
                LocalDate.now().toString()  // End date before start date
        );
        assertNull(discount);
        System.out.println("Test for invalid dates passed");
    }

    @Test
    @Order(5)
    void testCreateDiscountWithNullName() {
        Discount discount = DiscountFactory.createDiscount(
                null,
                "Percentage",
                "15%",
                LocalDate.now().toString(),
                LocalDate.now().plusDays(10).toString()
        );
        assertNull(discount);
        System.out.println("Test for null name passed");
    }
}