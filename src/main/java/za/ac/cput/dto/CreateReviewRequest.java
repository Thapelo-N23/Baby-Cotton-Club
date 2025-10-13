package za.ac.cput.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateReviewRequest {
    @NotNull
    private Integer productId;
    private Integer orderId;
    private Integer orderLineId;
    @Min(1)
    @Max(5)
    @NotNull
    private Short rating;
    @Size(max = 1000)
    private String reviewComment;
    // getters + setters
    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }
    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }
    public Integer getOrderLineId() { return orderLineId; }
    public void setOrderLineId(Integer orderLineId) { this.orderLineId = orderLineId; }
    public Short getRating() { return rating; }
    public void setRating(Short rating) { this.rating = rating; }
    public String getReviewComment() { return reviewComment; }
    public void setReviewComment(String reviewComment) { this.reviewComment = reviewComment; }
}
