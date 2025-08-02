/*
 * Baby Cotton Club
 * Category POJO Class
 * Author: Olwethu Nene (230277845)
 * Date: 2025/05/11
 */

package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int categoryId;
    protected String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> product;

    protected Category() {}

    private Category(Builder builder) {
        this.categoryId = builder.categoryId;
        this.categoryName = builder.categoryName;
        this.product = builder.product;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<Product> getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", product=" + product +
                '}';
    }

    public static class Builder {
        private int categoryId;
        private String categoryName;
        private List<Product> product;

        public Builder setCategoryId(int categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder setCategoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }

        public Builder setProduct(List<Product> product) {
            this.product = product;
            return this;
        }

        public Builder copy(Category category) {
            this.categoryId = category.categoryId;
            this.categoryName = category.categoryName;
            this.product = category.product;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }
}
