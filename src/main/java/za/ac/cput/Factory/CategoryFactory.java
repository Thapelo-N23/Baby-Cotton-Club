package za.ac.cput.Factory;

import za.ac.cput.domain.Category;
import za.ac.cput.util.Helper;

public class CategoryFactory {
    public static Category createCategory(Integer categoryId, String categoryName) {
        if ( Helper.isNullOrEmpty(categoryName)) {
            return null;
        }
        if(!Helper.isValidCategoryId(categoryId)){
            return null;
        }
        return new Category.Builder().setCategoryId(categoryId).setCategoryName(categoryName).build();
    }

}
