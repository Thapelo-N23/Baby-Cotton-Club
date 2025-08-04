/*
package za.ac.cput.service.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Discount;
import za.ac.cput.factory.DiscountFactory;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DiscountServiceTest {

    @Autowired
    private DiscountService discountService;

    private static Discount discount;

    @BeforeAll
    static void setUp() {
        discount = DiscountFactory.createDiscount(
                "Winter Sale",
                "Percentage",
                "15%",
                "2025-08-01",
                "2025-08-31",
                null);
    }
}

 */