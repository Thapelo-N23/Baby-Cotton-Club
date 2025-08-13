//package za.ac.cput.service.impl;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import za.ac.cput.domain.Category;
//import za.ac.cput.factory.CategoryFactory;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CategoryServiceTest {
//
//@Autowired
//private CategoryService service;
//private Category category  = CategoryFactory.createCategory("Clothes");
//
//    @Test
//    void create() {
//        Category created = service.create(category);
//        assertNotNull(created);
//        System.out.println("Created Category: " + created);
//    }
//
//    @Test
//    void read() {
//        Category readCategory = service.read(category.getCategoryId());
//        assertNotNull(readCategory);
//        System.out.println("Read Category: " + readCategory);
//    }
//
//    @Test
//    void update() {
//        Category updatedCategory = new Category.Builder()
//                .copy(category)
//                .setCategoryName("Updated Clothes")
//                .build();
//
//        Category updated = service.update(updatedCategory);
//        assertNotNull(updated);
//        System.out.println("Updated Category: " + updated);
//    }
//
//    @Test
//    void getAll() {
//        assertNotNull(service.getAll());
//        System.out.println("All Categories: " + service.getAll());
//    }
//}