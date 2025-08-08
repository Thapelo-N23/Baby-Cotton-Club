/*
 * DiscountFactoryTest POJO Class
 * Author: O Ntsaluba (230741754)
 * Date: 30 July 2025
 */

package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Discount;

import static org.junit.jupiter.api.Assertions.*;

class DiscountFactoryTest {

    private static Discount discount1;
    private static Discount discount2;

    @BeforeAll
    public static void setup() {

        discount1 = DiscountFactory.createDiscount(
                "Winter Sale",
                "Percentage",
                "20%",
                "20250801",
                "20250831",
                null
        );

        discount2 = DiscountFactory.createDiscount(
                "Summer Special",
                "Fixed",
                "R50 off",
                "20250801",
                "20250815",
                null
        );
    }

    @Test
    public void testCreateValidDiscount1() {
        assertNotNull(discount1);
        assertEquals("Winter Sale", discount1.getDiscountName());
        assertEquals("Percentage", discount1.getDiscountType());
        System.out.println("Discount 1: " + discount1);
    }

    @Test
    public void testCreateValidDiscount2() {
        assertNotNull(discount2);
        assertEquals("Summer Special", discount2.getDiscountName());
        assertEquals("Fixed", discount2.getDiscountType());
        System.out.println("Discount 2: " + discount2);
    }

    @Test
    public void testCreateDiscountWithEmptyName() {
        Discount discount = DiscountFactory.createDiscount(
                "",
                "Fixed",
                "R50 off",
                "20250801",
                "20250806",
                null
        );
        assertNull(discount);
        System.out.println("Test for empty name passed");
    }


    @Test
    public void testCreateDiscountWithNullName() {
        Discount discount = DiscountFactory.createDiscount(
                null,
                "Percentage",
                "15%",
                "20250801",
                "20250810",
                null
        );
        assertNull(discount);
        System.out.println("Test for null name passed");
    }
}
