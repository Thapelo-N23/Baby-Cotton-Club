/**
 * BabyCottonClub
 * Product.java
 * Author : Thapelo Ngwenya - 222260971
 * Date : 11 May 2025
 */
package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @JsonProperty("productName")
    @Column(name = "product_name")
    protected String productName;
    protected String color;
    protected short price;
    @JsonProperty("inStock")
    @Column(name = "in_stock")
    protected String inStock;

    @OneToMany(mappedBy="product", fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("product-reviews")
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference("category-products")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    @JsonBackReference("supplier-products")
    private Supplier supplier;

    @Column(name = "image_url", columnDefinition = "LONGTEXT")
    private String imageUrl;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_sizes", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "size")
    private List<String> sizes = new ArrayList<>();


    private Product(Builder builder) {
        this.productId = builder.productId;
        this.productName = builder.productName;
        this.color = builder.color;
        this.price = builder.price;
        this.inStock = builder.inStock;
        this.category = builder.category;
        this.reviews = builder.reviews;
        this.supplier = builder.supplier;
        this.imageUrl = builder.imageUrl;
        this.sizes = builder.sizes;


    }
    public Product() {

    }


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public Category getCategory() {  return category; }

    public Supplier getSupplier() {
        return supplier;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getSizes() {
        return sizes;
    }

    // Add setters to allow controllers to attach managed Category/Supplier before save
    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", inStock='" + inStock + '\'' +
                ", reviews=" + reviews +
                ", category=" + category +
                ", supplier=" + supplier +
                ", imageUrl='" + imageUrl + '\'' +
                ", sizes=" + sizes +
                '}';
    }



    public static class Builder {
        private int productId;
        private String productName;
        private String color;
        private short price;
        private String inStock;
        private Category category;
        private List<Review> reviews = new ArrayList<>();
        private Supplier supplier;
        private String imageUrl;
        private List<String> sizes = new ArrayList<>();

        public Builder setCategory(Category category) {
            this.category = category;
            return this;
        }

        public Builder setSupplier(Supplier supplier) {
            this.supplier = supplier;
            return this;
        }

        public Builder setProductId(int productId) {
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

        public Builder setReview(Review review) {
            if (review != null) {
                this.reviews = Collections.singletonList(review);
            } else {
                this.reviews = new ArrayList<>();
            }
            return this;
        }

        public Builder setReviews(List<Review> reviews) {
            this.reviews = reviews;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setSizes(List<String> sizes) {
            if (sizes != null) this.sizes = sizes;
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
                    ", category=" + category +
                    ", reviews=" + reviews +
                    ", supplier=" + supplier +
                    ", imageUrl='" + imageUrl + '\'' +
                    ", sizes=" + sizes +
                    '}';
        }

        public Builder copy(Product product) {
            this.productId = product.getProductId();
            this.productName = product.getProductName();
            this.color = product.getColor();
            this.price = product.getPrice();
            this.inStock = product.getInStock();
            this.category = product.getCategory();
            this.reviews = product.getReviews();
            this.supplier = product.getSupplier();
            this.imageUrl = product.getImageUrl();
            this.sizes = product.getSizes();
            return this;
        }
        public Product build() {
            return new Product(this);
        }
    }
}
