/*
Review.java
Review POJO class
Author: Olwethu Nene(230277845)
Date: 21 July 2025
 */

package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reviews")

public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer reviewId;
    private Integer customerId;
    private Integer productId;
    private short rating;
    private String reviewComment;
    private LocalDate reviewDate;


    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    protected Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    protected Product product;

    protected Review(){
    }
    public Review (Builder builder){
        this.reviewId = builder.reviewId;
        this.customerId = builder.customerId;
        this.productId = builder.productId;
        this.rating = builder.rating;
        this.reviewComment = builder.reviewComment;
        this.reviewDate = builder.reviewDate;
        this.customer = builder.customer;
        this.product = builder.product;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public Integer getCustomerId() {
        return customerId;}

    public Integer getProductId() {
        return productId;}

    public short getRating() {
        return rating;
    }

    public String getReviewComment() {

        return reviewComment;
    }

    public LocalDate getReviewDate() {

        return reviewDate;
    }

    public Customer getCustomer() {
        return customer;
    }
    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", customerId=" + customerId +
                ", productId=" + productId +
                ", rating=" + rating +
                ", reviewComment='" + reviewComment + '\'' +
                ", reviewDate=" + reviewDate +
                ", customer=" + customer +
                ", product=" + product +
                '}';
    }


    public static class Builder{
        private Integer reviewId;
        private Integer customerId;
        private Integer productId;
        private short rating;
        private LocalDate reviewDate;
        private String reviewComment;
        private Customer customer;
        private Product product;

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
        public Builder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }
        public Builder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder copy(Review builder){
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