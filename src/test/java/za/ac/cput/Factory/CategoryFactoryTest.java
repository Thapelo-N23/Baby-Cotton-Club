package za.ac.cput.factory;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import za.ac.cput.domain.Category;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryFactoryTest {

    private static Category category1 = CategoryFactory.createCategory(3, "Clothes");
    private static Category category2 = CategoryFactory.createCategory(4, "Footwear");

    @Test
    @Order(1)
    public void testCreateCategory() {
        assertNotNull(category1);
        System.out.println(category1);
    }

    @Test
    @Order(2)
    public void createCategoryWithAllAttributes() {
        assertNotNull(category2);
        System.out.println(category2);
    }
}
