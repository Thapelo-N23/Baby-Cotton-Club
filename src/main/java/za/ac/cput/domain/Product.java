/**
 * BabyCottonClub
 * Product.java
 * Author : Thapelo Ngwenya - 222260971
 * Date : 11 May 2025
 */
package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    protected String productName;
    protected String color;
    protected short price;
    protected String inStock;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;
    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;

 @ManyToOne
 @JoinColumn(name = "category_id")
 private Category category;



 private Product(Builder builder) {
        this.productId = builder.productId;
        this.productName = builder.productName;
        this.color = builder.color;
        this.price = builder.price;
        this.inStock = builder.inStock;
        this.reviews = builder.reviews;
        this.shipment = builder.shipment;
        this.category = builder.category;


 }
    public Product() {

    }


    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getColor() {
        return color;
    }

    public short getPrice() {
        return price;
    }

    public String getInStock() {
        return inStock;
    }

    public List<Review> getReviews() {  return reviews;}

    public Shipment getShipment() { return shipment;}

    public Category getCategory() {  return category; }




 @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", inStock='" + inStock + '\'' +
                ", reviews=" + reviews +
                ", shipment=" + shipment +
                '}';
    }

    public static class Builder {
        private Long productId;
        private String productName;
        private String color;
        private short price;
        private String inStock;
        private List<Review> reviews;
        private Shipment shipment;
        private Category category;

        public Builder setCategory(Category category) {
        this.category = category;
        return this;
       }

     public Builder setReviews(List<Review> reviews) {
            this.reviews = reviews;
            return this;
        }

        public Builder setShipment(Shipment shipment) {
            this.shipment = shipment;
            return this;
        }

     public Builder setProductId(Long productId) {
      this.productId = productId;
      return this;
     }


     public Builder setProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Builder setPrice(short price) {
            this.price = price;
            return this;
        }

        public Builder setInStock(String inStock) {
            this.inStock = inStock;
            return this;
        }


     @Override
     public String toString() {
      return "Builder{" +
              "productId=" + productId +
              ", productName='" + productName + '\'' +
              ", color='" + color + '\'' +
              ", price=" + price +
              ", inStock='" + inStock + '\'' +
              ", reviews=" + reviews +
              ", shipment=" + shipment +
              ", category=" + category +
              '}';
     }

     public Builder copy(Product product) {
            this.productId = product.getProductId();
            this.productName = product.getProductName();
            this.color = product.getColor();
            this.price = product.getPrice();
            this.inStock = product.getInStock();
            this.reviews = product.getReviews();
            this.category = product.getCategory();
         return this;
        }
        public Product build() {
            return new Product(this);
        }
    }
}
