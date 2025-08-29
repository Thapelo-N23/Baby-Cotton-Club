package za.ac.cput.factory;

import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Product;

public class CartItemFactory {
    public static CartItem createCartItem(Cart cart, Product product, int quantity) {
        if ( quantity <= 0) return null;


        return new CartItem.Builder()
                .setCart(cart)
                .setProduct(product)
                .setQuantity(quantity)
                .build();
    }
}

