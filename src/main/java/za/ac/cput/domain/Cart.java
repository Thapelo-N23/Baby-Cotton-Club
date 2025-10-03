/**
 * BabyCottonClub
 * Cart.java
 * Author : Onako Ntsaluba - 230741754
 */

package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table(name = "carts")

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL ,fetch = FetchType.EAGER)


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
    public void setCartId(int cartId) { this.cartId = cartId; }
    public void setCustomer(Customer customer) { this.customer = customer; }

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
