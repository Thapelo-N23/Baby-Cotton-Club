package za.ac.cput.Factory;

import za.ac.cput.domain.Review;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class ReviewFactory {
    public static Review createReview( Integer reviewId,Integer customerId,Integer productId,
                                       short rating, String reviewComment, String reviewDate){
        if(Helper.isNullOrEmpty(reviewComment)){
            return null;
        }
        if(!Helper.isValidReviewId(reviewId)){
            return null;
        }

        LocalDate date = Helper.isValidDate(reviewDate);
        if(date == null){
            return null;
        }

        return new Review.Builder()
                .setReviewId(reviewId)
                .setCustomerId(customerId)
                .setProductId(productId)
                .setReviewComment(reviewComment)
                .setReviewDate(date)
                .build();
    }
}
