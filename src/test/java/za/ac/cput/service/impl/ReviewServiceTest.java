/*
ReviewService.java
ReviewService POJO class
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
import za.ac.cput.domain.Review;
import za.ac.cput.factory.ReviewFactory;


@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class ReviewServiceTest {
    @Autowired
    private ReviewService service;
    private Review review = ReviewFactory.createReview(12, 1, 5, (short)5, "Great product!", "2023-10-01");

    @Test
    void create() {
        Review created = service.create(review);
        assertNotNull(created);
        System.out.println("Created: " + created);
    }

    @Test
    void read() {
        Review read = service.read(review.getReviewId());
        assertNotNull(read);
        System.out.println("Read: " + read);
    }

    @Test
    void update() {
        Review updatedReview = new Review.Builder()
                .copy(review)
                .setReviewComment("Updated review text")
                .setRating((short)4)
                .build();
        Review updated = service.update(updatedReview);
        assertNotNull(updated);
        System.out.println("Updated: " + updated);
    }

    @Test
    void getAll() {
        assertFalse(service.getAll().isEmpty(), "Review list should not be empty");
        System.out.println("All Reviews: " + service.getAll());
    }
}