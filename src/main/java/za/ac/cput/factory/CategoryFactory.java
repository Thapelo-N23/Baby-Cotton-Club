/*
Category.java
CategoryFactory POJO class
Author: Olwethu Nene
Student number:(230277845)
Date: 18 May 2025
 */
package za.ac.cput.factory;

import za.ac.cput.domain.Category;
import za.ac.cput.domain.Product;
import za.ac.cput.util.Helper;


public class CategoryFactory {
    private static int categoryCounter = 1;

    public static Category createCategory(String categoryName, Product product) {
        if (Helper.isNullOrEmpty(categoryName)) {
            return null;

        }
        return new Category.Builder()
                .setCategoryId(categoryCounter++).setCategoryName(categoryName).setProduct(product).build();
    }


}