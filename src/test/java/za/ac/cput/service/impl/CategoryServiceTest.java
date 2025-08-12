package za.ac.cput.service.impl;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Category;
import za.ac.cput.factory.CategoryFactory;
import za.ac.cput.service.ICategoryService;

import static org.junit.jupiter.api.Assertions.*;
import static za.ac.cput.factory.CategoryFactoryTest.products;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryServiceTest {

    @Autowired
    private ICategoryService service;
    private static Category category;
    //private Category category  = CategoryFactory.createCategory("Clothes",products);


    @Test
    @Order(1)
    void create() {
        category = CategoryFactory.createCategory("Clothes", products);
        assertNotNull(category.getCategoryId(), "Category ID should not be null after creation");

        // Uncomment the following lines if you want to test creating a category
        // This assumes that the service is properly set up to handle category creation
//        Category created = service.create(category);
//        assertNotNull(created);
//        System.out.println(created);
    }

    @Test
    @Order(2)
    void read() {
        category = CategoryFactory.createCategory("Clothes", products);
        assertNotNull(category.getCategoryId(), "Category ID should not be null after creation");

        // Uncomment the following lines if you want to test reading a category
        // This assumes that the category has been created in the database
//        service.create(category);
//        Category readCategory = service.read(category.getCategoryId());
//        assertNotNull(readCategory);
//        System.out.println(readCategory);
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
        System.out.println(updated);
    }

    @Test
    @Order(4)
    void getAll() {
        category = CategoryFactory.createCategory("Clothes", products);
        service.create(category);
        assertFalse(service.getAll().isEmpty(), "The list of categories should not be empty");
        System.out.println("All Categories: " + category);

    }
}