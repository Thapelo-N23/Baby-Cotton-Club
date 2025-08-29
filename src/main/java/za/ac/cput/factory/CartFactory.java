package za.ac.cput.factory;

import za.ac.cput.domain.Cart;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.CartItem;
import java.util.List;

public class CartFactory {
    public static Cart createCart(Customer customer, List<CartItem> items) {



        return new Cart.Builder()
                .setCustomer(customer)
                .setItems(items)
                .setCheckedOut(false)
                .build();
    }
}

