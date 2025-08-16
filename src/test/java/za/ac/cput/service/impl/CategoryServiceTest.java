package za.ac.cput.service.impl;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Category;
import za.ac.cput.domain.Product;
import za.ac.cput.factory.CategoryFactory;
import za.ac.cput.service.ICategoryService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryServiceTest {

    @Autowired
    private ICategoryService service;

    private static final List<Product> products = new ArrayList<>();
    private Category category  = CategoryFactory.createCategory("Clothes",products);

    @Test
    @Order(1)
    void create() {
        Category created = service.create(category);
        assertNotNull(created);
        System.out.println("Created Category: " + created);
    }

    @Test
    @Order(2)
    void read() {
        Category readCategory = service.read(category.getCategoryId());
        //assertNotNull(readCategory);
        System.out.println("Read Category: " + readCategory);
    }

    @Test
    @Order(3)
    void update() {
        Category updatedCategory = new Category.Builder()
                .copy(category)
                .setCategoryName("Updated Clothes")
                .build();

        Category updated = service.update(updatedCategory);
        assertNotNull(updated);
        System.out.println("Updated Category: " + updated);
    }

    @Test
    @Order(4)
    void getAll() {
        assertNotNull(service.getAll());
        System.out.println("All Categories: " + category);
    }
}