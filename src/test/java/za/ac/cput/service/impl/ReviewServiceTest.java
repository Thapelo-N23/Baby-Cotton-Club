package za.ac.cput.service.impl;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.Main;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Review;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.factory.ProductFactory;
import za.ac.cput.factory.ReviewFactory;
import za.ac.cput.service.ICustomerService;
import za.ac.cput.service.IReviewService;
import za.ac.cput.service.ProductService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes =Main.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class ReviewServiceTest {
    @Autowired
private IReviewService service;

@Autowired
private ICustomerService customerService;

@Autowired
private ProductService productService;

private static Review review;
private static Customer customer;
private static Product product;


    @Test
    @Order(1)
    void create() {
        customer = CustomerFactory.createCustomer("John", "Doe", "olwethunene42@gmail.com",
                "0781234567",
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList());

        Customer create = customerService.create(customer);
        assertNotNull(create);
        customer = create;
        System.out.println("Created Customer" + create);

        product = ProductFactory.createProduct("Nike","Blue",
                (short)67,"Yes");

        Product create1 = productService.create(product);
        assertNotNull(create1);
        product = create1;
        System.out.println("Product created: " + create1);


        review = ReviewFactory.createReview( (short)5,"Great product",
                "2025-07-22",customer, product);

        Review created = service.create(review);
        assertNotNull(created);
        review = created;
        System.out.println("Created Review: " + created);

    }

    @Test
    @Order(2)
    void read() {
        assertNotNull(review, "Review object from create() should not be null");
        Review readReview = service.read(review.getReviewId());
        assertNotNull(readReview);
        System.out.println("Read Review: " + readReview);
    }

    @Test
    @Order(3)
    void update() {
        Review updatedReview = new Review.Builder()
                .copy(review)
                .setRating((short) 4)
                .setReviewComment("Good product")
                .setCustomer(customer)
                .setProduct(product)
                .build();

        Review updated = service.update(updatedReview);
        assertNotNull(updated);
        System.out.println("Updated Review: " + updated);
    }

    @Test
    @Order(4)
    void getAll() {
        assertNotNull(service.getAll());
        System.out.println("All Reviews: " + review);
    }
}