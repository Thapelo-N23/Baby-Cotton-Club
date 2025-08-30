/**
 * BabyCottonClub
 * CartItem.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date : 29 August 2025
 */

package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")

public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartItemId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
    @JsonIgnore

    private Cart cart;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private int quantity;

    public CartItem() {}

    public CartItem(Builder builder) {
        this.cartItemId = builder.cartItemId;
        this.cart = builder.cart;
        this.product = builder.product;
        this.quantity = builder.quantity;
    }

    public int getCartItemId() { return cartItemId; }
    public Cart getCart() { return cart; }
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemID=" + cartItemId +
                ", cart=" + (cart != null) +
                ", product=" + (product != null) +
                ", quantity=" + quantity +
                '}';
    }

    public static class Builder {
        private int cartItemId;
        private Cart cart;
        private Product product;
        private int quantity;

        public Builder setCartItemId(int cartItemId) { this.cartItemId = cartItemId; return this; }
        public Builder setCart(Cart cart) { this.cart = cart; return this; }
        public Builder setProduct(Product product) { this.product = product; return this; }
        public Builder setQuantity(int quantity) { this.quantity = quantity; return this; }

        public Builder copy(CartItem cartItem) {
            this.cartItemId = cartItem.cartItemId;
            this.cart = cartItem.cart;
            this.product = cartItem.product;
            this.quantity = cartItem.quantity;
            return this;
        }

        public CartItem build() { return new CartItem(this); }
    }
}
