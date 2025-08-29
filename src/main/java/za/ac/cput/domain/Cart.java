/**
 * BabyCottonClub
 * Cart.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date : 29 August 2025
 */

package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> items;

    public boolean isCheckedOut;

    public Cart() {}

    public Cart(Builder builder) {
        this.cartId = builder.cartId;
        this.customer = builder.customer;
        this.items = builder.items;
        this.isCheckedOut = builder.isCheckedOut;
    }

    public int getCartId() { return cartId; }
    public Customer getCustomer() { return customer; }
    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) { this.items = items; }
    public boolean isCheckedOut() { return isCheckedOut; }
    public void setCheckedOut(boolean checkedOut) { this.isCheckedOut = checkedOut; }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", customer=" + (customer != null ? customer.getCustomerId() : null) +
                ", items=" + (items != null && !items.isEmpty()) +
                '}';
    }

    public static class Builder {
        private int cartId;
        private Customer customer;
        private List<CartItem> items;
        private boolean isCheckedOut;

        public Builder setCartId(int cartId) { this.cartId = cartId; return this; }
        public Builder setCustomer(Customer customer) { this.customer = customer; return this; }
        public Builder setItems(List<CartItem> items) { this.items = items; return this; }
        public Builder setCheckedOut(boolean checkedOut) { this.isCheckedOut = checkedOut; return this; }

        public Builder copy(Cart cart) {
            this.cartId = cart.cartId;
            this.customer = cart.customer;
            this.items = cart.items;
            this.isCheckedOut = cart.isCheckedOut;
            return this;
        }

        public Cart build() { return new Cart(this); }
    }
}
