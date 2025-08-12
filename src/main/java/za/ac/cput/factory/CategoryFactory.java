package za.ac.cput.factory;

import za.ac.cput.domain.Category;
import za.ac.cput.domain.Product;
import za.ac.cput.util.Helper;

import java.util.List;

public class CategoryFactory {

    public static Category createCategory(String categoryName, List<Product> products) {
        if (Helper.isNullOrEmpty(categoryName)) {
            return null;
        }
        if (products == null) {
            return null;
        }
        return new Category.Builder()
                .setCategoryName(categoryName).setProducts(products)
                .build();
    }
}


