/*
CategoryFactoryTest POJO class
Author: Olwethu Nene
Student number: 230277845
Date: 30 June 2025
 */

package za.ac.cput.factory;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import za.ac.cput.domain.Category;
import za.ac.cput.domain.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static za.ac.cput.factory.ReviewFactoryTest.product1;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryFactoryTest {

    private static final List<Product> products = new ArrayList<>();

    private static final Category category1 = CategoryFactory.createCategory("Clothes");
    private static final Category category2 = CategoryFactory.createCategory("Shoes");

    @Test
    @Order(1)
    void createCategory() {
        assertNotNull(category1);
        System.out.println(category1);
    }

    @Test
    @Order(2)
    void createTestThatPasses() {
        assertNotNull(category2);
        System.out.println(category2);
    }

    @Test
    @Order(3)
    void testCreateCategoryWithProducts() {
        products.add(product1);
        Category categoryWithProducts = CategoryFactory.createCategory("Clothes", products);
        assertNotNull(categoryWithProducts);
        assertEquals("Clothes", categoryWithProducts.getCategoryName());
        assertEquals(products, categoryWithProducts.getProducts());
        System.out.println("Category with Products: " + categoryWithProducts);
    }
}

