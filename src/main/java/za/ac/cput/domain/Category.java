/*
Category.java
Category POJO class
Author: Olwethu Nene(230277845)
Date: 11 May 2025
 */

package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")

public class Category {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer categoryId;
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;

    protected Category() {

    }
public Category(Builder builder){
        this.categoryId = builder.categoryId;
        this.categoryName = builder.categoryName;
}

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    public static class Builder {
        private Integer categoryId;
        private String categoryName;

        public Builder setCategoryId(Integer categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder setCategoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }

        public Builder copy(Category builder) {
            this.categoryId = builder.categoryId;
            this.categoryName = builder.categoryName;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }
    }





