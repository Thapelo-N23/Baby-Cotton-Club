/*
Review.java
ReviewFactory POJO class
Author: Olwethu Nene
Student number:(230277845)
Date: 18 May 2025
 */
package za.ac.cput.factory;

import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Review;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class ReviewFactory {

    private static int reviewCounter = 1;

    public static Review createReview(short rating, String reviewComment, String reviewDate, Customer customer, Product product){
        if(Helper.isNullOrEmpty(reviewComment)){
            return null;
        }

        LocalDate date = Helper.isValidDate(reviewDate);
        if(date == null){
            return null;
        }

        return new Review.Builder().setRating(rating)
                .setReviewId(reviewCounter++)
                .setReviewComment(reviewComment)
                .setReviewDate(date).setCustomer(customer).setProduct(product)
                .build();
    }
}