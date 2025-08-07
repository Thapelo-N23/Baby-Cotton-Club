//package za.ac.cput.service.impl;
//
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import za.ac.cput.Main;
//import za.ac.cput.domain.Customer;
//import za.ac.cput.domain.Product;
//import za.ac.cput.domain.Review;
//import za.ac.cput.factory.CustomerFactory;
//import za.ac.cput.factory.ProductFactory;
//import za.ac.cput.factory.ReviewFactory;
//import za.ac.cput.service.ProductService;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(classes =Main.class)
//@TestMethodOrder(MethodOrderer.MethodName.class)
//
//class ReviewServiceTest {
//
//    @Autowired
//private  ReviewService service;
//@Autowired
//private  CustomerService service1;
//@Autowired
//private ProductService service2;
//
//private Review review = ReviewFactory.createReview( (short)5,"Great product" ,  "2025-07-22",null, null);
//private final Customer customer = CustomerFactory.createCustomer("John", "Doe", "olwethunene42@gmail.com",
//        "0781234567", null, null, null);
//private final Product product = ProductFactory.createProduct(6789L,"Nike","Blue", (short)3, "Yes");
//
//    @Test
//    void create() {
//Review created = service.create(review);
//        assertNotNull(created);
//        System.out.println("Created Review: " + created);
//    }
//
//    @Test
//    void read() {
//        Review readReview = service.read(review.getReviewId());
//        assertNotNull(readReview);
//        System.out.println("Read Review: " + readReview);
//    }
//
//    @Test
//    void update() {
//        Review updatedReview = new Review.Builder()
//                .copy(review)
//                .setRating((short) 4)
//                .setReviewComment("Good product")
//                .setCustomer(customer)
//                .setProduct(product)
//                .build();
//
//        Review updated = service.update(updatedReview);
//        assertNotNull(updated);
//        System.out.println("Updated Review: " + updated);
//    }
//
//    @Test
//    void getAll() {
//        assertNotNull(service.getAll());
//        System.out.println("All Reviews: " + service.getAll());
//    }
//}