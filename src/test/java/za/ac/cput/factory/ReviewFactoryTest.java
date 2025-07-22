package za.ac.cput.factory;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import za.ac.cput.domain.Review;
import za.ac.cput.factory.ReviewFactory;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReviewFactoryTest {

    private static Review review1 = ReviewFactory.createReview(3, 44, 4, (short) 10,
            "Good", "20250516");

    private static Review review2 = ReviewFactory.createReview(2, 13, 10, (short) 12,
            "Bad", "20250115");

    private static Review review3 = ReviewFactory.createReview(2, 2, 34, (short) 6,
            "The material is great", "20250505");

    @Test
    @Order(1)
    public void testCreateReview() {
        assertNotNull(review1);
        System.out.println(review1);
    }

    @Test
    @Order(2)
    public void testWithAllAttributes() {
        assertNotNull(review2);
        System.out.println(review2);
    }

    @Test
    @Order(3)
    public void testWithReviewThatFail() {
        assertNotNull(review3);
        System.out.println(review3);
    }
}
