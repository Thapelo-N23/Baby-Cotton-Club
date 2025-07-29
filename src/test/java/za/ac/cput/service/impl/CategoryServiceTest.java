/*
CategoryService.java
CategoryService POJO class
Author: Olwethu Nene
Student number:(230277845)
Date: 21 July 2025
 */

package za.ac.cput.service.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Category;
import za.ac.cput.factory.CategoryFactory;


@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)

class CategoryServiceTest {
    @Autowired
    private CategoryService service;
    private Category category = CategoryFactory.createCategory(12,"Clothes");

    @Test
    void create() {
        Category created = service.create(category);
        assertNotNull(created);
        System.out.println("Created: " + created);
    }

    @Test
    void read() {
        Category read = service.read(category.getCategoryId());
        assertNotNull(read);
        System.out.println("Read: " + read);
    }

    @Test
    void update() {
        Category newCategory = new Category.Builder()
                .copy(category)
                .setCategoryId(12).setCategoryName("shoes")
                .build();
        Category updated = service.update(newCategory);
        assertNotNull(updated);
        System.out.println("Updated: " + updated);
    }

    @Test
    void getAll() {
        System.out.println("All Categories: " + service.getAll());
        assertFalse(service.getAll().isEmpty(), "Category list should not be empty");
    }
}

