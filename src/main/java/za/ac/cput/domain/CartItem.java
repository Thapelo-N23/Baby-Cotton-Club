/**
 * BabyCottonClub
 * CartItem.java
 * Author : Onako Ntsaluba - 230741754
 */

package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "size")
    private String size;

    public CartItem() {}

    public CartItem(Builder builder) {
        this.cartItemId = builder.cartItemId;
        this.cart = builder.cart;
        this.product = builder.product;
        this.quantity = builder.quantity;
        this.size = builder.size;
    }

    public int getCartItemId() { return cartItemId; }
    public Cart getCart() { return cart; }
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public String getSize() { return size; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setCart(Cart cart) { this.cart = cart; }
    public void setProduct(Product product) { this.product = product; }
    public void setSize(String size) { this.size = size; }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemID=" + cartItemId +
                ", cart=" + (cart != null) +
                ", product=" + (product != null) +
                ", quantity=" + quantity +
                ", size='" + size + '\'' +
                '}';
    }

    public static class Builder {
        private int cartItemId;
        private Cart cart;
        private Product product;
        private int quantity;
        private String size;

        public Builder setCartItemId(int cartItemId) { this.cartItemId = cartItemId; return this; }
        public Builder setCart(Cart cart) { this.cart = cart; return this; }
        public Builder setProduct(Product product) { this.product = product; return this; }
        public Builder setQuantity(int quantity) { this.quantity = quantity; return this; }
        public Builder setSize(String size) { this.size = size; return this; }

        public Builder copy(CartItem cartItem) {
            this.cartItemId = cartItem.cartItemId;
            this.cart = cartItem.cart;
            this.product = cartItem.product;
            this.quantity = cartItem.quantity;
            this.size = cartItem.size;
            return this;
        }

        public CartItem build() { return new CartItem(this); }
    }
}
