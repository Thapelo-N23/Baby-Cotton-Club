/**
 * BabyCottonClub
 * Shipment.java
 * Author : Thapelo Ngwenya - 222260971
 * Date : 11 May 2025
 */

package za.ac.cput.factory;

import za.ac.cput.domain.Product;
import za.ac.cput.util.Helper;

public class ProductFactory {
    public static Product createProduct(String productId, String productName, String color, short price, String inStock) {
        if (Helper.isNullOrEmpty(productId) || Helper.isNullOrEmpty(productName) || Helper.isNullOrEmpty(color)) {
            return null;
        }
        return new Product.Builder()
                .setProductId(productId)
                .setProductName(productName)
                .setColor(color)
                .setPrice(price)
                .setInStock(inStock)
                .build();
    }
}
//updated


