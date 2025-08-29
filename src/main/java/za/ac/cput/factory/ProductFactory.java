/**
 * BabyCottonClub
 * Shipment.java
 * Author : Thapelo Ngwenya - 222260971
 * Date : 11 May 2025
 */

package za.ac.cput.factory;

import za.ac.cput.domain.Product;
import za.ac.cput.domain.Review;
import za.ac.cput.util.Helper;

public class ProductFactory {

    public static Product createProduct(String productName, String color, short price, String inStock, Review review, za.ac.cput.domain.Supplier supplier) {
        if (Helper.isNullOrEmpty(productName) ||
                Helper.isNullOrEmpty(color) ||
                !Helper.isValidPrice(price) ||
                Helper.isNullOrEmpty(inStock)) {
            return null;
        }
        return new Product.Builder()
                .setProductName(productName)
                .setColor(color)
                .setPrice(price)
                .setInStock(inStock)
                .setReview(review)
                .setSupplier(supplier)
                .build();
    }
}


//updated
