package za.ac.cput.factory;

import za.ac.cput.domain.Category;
import za.ac.cput.domain.Product;
import za.ac.cput.util.Helper;

import java.util.List;

public class CategoryFactory {
    private static int categoryCounter = 1;

    public static Category createCategory(String categoryName) {
        if (Helper.isNullOrEmpty(categoryName)) {
            return null;
        }
        return new Category.Builder()
                .setCategoryId(categoryCounter++)
                .setCategoryName(categoryName)
                .build();
    }


    public static Category createCategory(String categoryName, List<Product> products) {
        if (Helper.isNullOrEmpty(categoryName) ) {
            return null;
        }
        return new Category.Builder()
                .setCategoryId(categoryCounter++)
                .setCategoryName(categoryName)
                .setProducts(products)
                .build();
    }
}
