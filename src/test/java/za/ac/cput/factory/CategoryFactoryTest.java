/*
Category.java
CategoryFactoryTest POJO class
Author: Olwethu Nene
Student number:(230277845)
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

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryFactoryTest {

    private static List<Product> products = new ArrayList<>();
    private static Category category1 = CategoryFactory.createCategory("Clothes", null);
    private static Product product1 = ProductFactory.createProduct("T-Shirt", "Blue", (short) 200, "Yes");

    @Test
    @Order(1)
    public void testCreateCategory() {
        assertNotNull(category1);
        System.out.println(category1);
    }

    @Test
    @Order(2)
    public void testCreateProduct() {
        assertNotNull(product1);
        System.out.println(product1);
    }

    @Test
    @Order(3)
    void testCreateCategoryWithProducts() {
        products.add(product1);
        Category categoryWithProducts = CategoryFactory.createCategory("Clothes", product1);
        assertNotNull(categoryWithProducts);
        assertEquals("Clothes", categoryWithProducts.getCategoryName());
        System.out.println("Category with Products: " + categoryWithProducts);
    }
}