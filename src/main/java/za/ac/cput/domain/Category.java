/*
 * Baby Cotton Club
 * Category POJO Class
 * Author: Olwethu Nene (230277845)
 * Date: 2025/05/11
 */

package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonCreator;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int categoryId;

    protected String categoryName;

    // New field for products
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("category-products")
    private List<Product> products;

    protected Category() {}

    private Category(Builder builder) {
        this.categoryId = builder.categoryId;
        this.categoryName = builder.categoryName;
        this.products = builder.products;
    }

    // JsonCreator to allow deserializing a simple String into a Category (e.g. "Bed")
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Category fromString(String categoryName) {
        return new Builder().setCategoryName(categoryName).build();
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    // Getter for products
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", products=" + products +
                '}';
    }

    public static class Builder {
        private int categoryId;
        private String categoryName;
        private List<Product> products;

        public Builder setCategoryId(int categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder setCategoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }


        public Builder setProducts(List<Product> products) {
            this.products = products;
            return this;
        }

        public Builder copy(Category category) {
            this.categoryId = category.categoryId;
            this.categoryName = category.categoryName;
            this.products = category.products;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }
}
