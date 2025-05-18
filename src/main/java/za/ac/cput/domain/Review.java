/*
Review.java
Review POJO class
Author: Olwethu Nene(230277845)
Date: 11 May 2025
 */

package za.ac.cput.domain;

import java.time.LocalDate;

public class Review {
    private Integer reviewId;
    private Integer customerId;
    private Integer productId;
    private short rating;
    private String reviewComment;
    private LocalDate reviewDate;

    public Review(){
    }
    public Review (Builder builder){
       this.reviewId = builder.reviewId;
       this.customerId = builder.customerId;
       this.productId = builder.productId;
       this.rating = builder.rating;
       this.reviewComment = builder.reviewComment;
       this.reviewDate = builder.reviewDate;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public short getRating() {
        return rating;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public static class Builder{
        private Integer reviewId;
        private Integer customerId;
        private Integer productId;
        private short rating;
        private LocalDate reviewDate;
        private String reviewComment;


    public Builder setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
        return this;
    }
        public Builder setCustomerId(Integer customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder setProductId(Integer productId) {
            this.productId = productId;
            return this;
        }

        public Builder setRating(short rating) {
            this.rating = rating;
            return this;
        }

        public Builder setReviewDate(LocalDate reviewDate) {
            this.reviewDate = reviewDate;
            return this;
        }

        public Builder setReviewComment(String reviewComment) {
            this.reviewComment = reviewComment;
            return this;
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "reviewId=" + reviewId +
                    ", customerId=" + customerId +
                    ", productId=" + productId +
                    ", rating=" + rating +
                    ", reviewDate='" + reviewDate + '\'' +
                    ", reviewComment='" + reviewComment + '\'' +
                    '}';
        }

        public Builder copy(Builder builder){
            this.reviewId = builder.reviewId;
            this.customerId = builder.customerId;
            this.productId = builder.productId;
            this.rating = builder.rating;
            this.reviewDate = builder.reviewDate;
            this.reviewComment = builder.reviewComment;
            return this;

    }
    public Review build(){
    return new Review(this);
       }
     }
}
