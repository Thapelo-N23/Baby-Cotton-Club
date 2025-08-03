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
    public static Product createProduct(Long productId, String productName, String color, short price, String inStock) {
        if (Helper.isNullOrEmpty(productName) || Helper.isNullOrEmpty(color) || Helper.isValidPrice(price) || Helper.isValidProductId(productId) || Helper.isNullOrEmpty(inStock))   {
            return null;
        }


        return new Product.Builder()
                .setProductId(Long.valueOf(productId))
                .setProductName(productName)
                .setColor(color)
                .setPrice(price)
                .setInStock(inStock)
                .build();
    }
}
//updated


