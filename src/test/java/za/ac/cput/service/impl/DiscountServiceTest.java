package za.ac.cput.service.impl;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Discount;
import za.ac.cput.domain.OrderLine;
import za.ac.cput.factory.DiscountFactory;
import za.ac.cput.factory.OrderLineFactory;
import za.ac.cput.service.IDiscountService;
import za.ac.cput.service.IOrderLineService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DiscountServiceTest {

    @Autowired
    private IDiscountService discountService;

    @Autowired
    private IOrderLineService orderLineService;

    private static OrderLine orderLine;
    private static Discount discount;


    @Test
    @Order(1)
    void create() {

        orderLine = orderLineService.create(
                OrderLineFactory.createOrderLine(2, 50.00)
        );
        assertNotNull(orderLine.getOrderLineId(), "OrderLine ID should not be null");

        String startDate = "20250801";
        String endDate = "20250831";

        discount = discountService.create(
                DiscountFactory.createDiscount(
                        "Winter Sale",
                        "Percentage",
                        "15%",
                        startDate,
                        endDate,
                        orderLine)
        );
        assertNotNull(discount.getDiscountId(), "Discount ID should not be null");
        assertNotNull(discount.getStartDate(), "Start date should not be null");
        assertNotNull(discount.getEndDate(), "End date should not be null");

        System.out.println("Created Discount: " + discount);
    }

    @Test
    @Order(2)
    void read() {
        assertNotNull(discount, "Discount must be created before reading");
        Discount readDiscount = discountService.read(discount.getDiscountId());
        assertNotNull(readDiscount, "Read discount should not be null");
        assertEquals(discount.getDiscountId(), readDiscount.getDiscountId());
        System.out.println("Read Discount: " + readDiscount);
    }

    @Test
    @Order(3)
    void update() {

        Discount existingDiscount = discountService.read(discount.getDiscountId());
        assertNotNull(existingDiscount, "Existing discount should not be null");

        Discount updatedDiscount = new Discount.Builder()
                .copy(existingDiscount)
                .setDiscountName("School Sale")
                .setDiscountType("Percentage")
                .setDiscountValue("20%")
                .setStartDate(LocalDate.of(2024, 6, 3))
                .setEndDate(LocalDate.of(2025, 9, 15))
                .build();

        Discount updated = discountService.update(updatedDiscount);
        assertNotNull(updated, "Updated discount should not be null");
        assertEquals("School Sale", updated.getDiscountName());
        assertEquals("20%", updated.getDiscountValue());

        discount = updated;

        System.out.println("Updated Discount: " + updated);
    }


    @Test
    @Order(4)
    void getAll() {
        List<Discount> allDiscounts = discountService.getAll();
        assertNotNull(allDiscounts, "Discount list should not be null");
        assertFalse(allDiscounts.isEmpty(), "Discount list should not be empty");
        System.out.println("All Discounts: " + allDiscounts);
    }
}