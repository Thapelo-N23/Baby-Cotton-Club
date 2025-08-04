//package za.ac.cput.service.impl;
//
//import org.junit.jupiter.api.Test;
//import za.ac.cput.domain.Customer;
//import za.ac.cput.domain.Product;
//import za.ac.cput.domain.Review;
//import za.ac.cput.factory.CustomerFactory;
//import za.ac.cput.factory.ProductFactory;
//import za.ac.cput.factory.ReviewFactory;
//import za.ac.cput.service.ProductService;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ReviewServiceTest {
//private static ReviewService service;
//private static CustomerService service1;
//private static ProductService service2;
//
//private Review review = ReviewFactory.createReview( (short)5,"Great product" ,  "2025-07-22",null, null);
//private Customer customer = CustomerFactory.createCustomer("John", "Doe", "olwethunene42@gmail.com",
//        "0781234567", null, null, null);
//private static Product product = ProductFactory.createProduct("Laptop", "High performance laptop", (short)15.0, "Yes");
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
//                .setReviewDate("2025-07/23")
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