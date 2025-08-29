/**
 * BabyCottonClub
 * Product.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date : 11 May 2025
 */

package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Address> addresses ;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<CustomerOrder> customerOrders ;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Review> reviews ;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cart cart;

    public Customer() {}

    public Customer(Builder builder) {
        this.customerId = builder.customerId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.password = builder.password;
        this.addresses = builder.addresses;
        this.customerOrders = builder.customerOrders;
        this.reviews = builder.reviews;
        this.cart = builder.cart;
    }

    public int getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getPassword() { return password; }
    public List<Address> getAddresses() { return addresses; }
    public List<CustomerOrder> getOrders() { return customerOrders; }
    public List<Review> getReviews() { return reviews; }
    public Cart getCart() { return cart; }

    public void setPassword(String password) { this.password = password; }
    public void setCart(Cart cart) { this.cart = cart; }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +

                ", AddressesLoaded=" + (addresses != null) +
                ", CustomerOrdersLoaded=" + (customerOrders != null) +
                ", ReviewsLoaded=" + (reviews != null) +
                '}';
    }


    public static class Builder {
        private int customerId;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private String password;
        private List<Address> addresses;
        private List<CustomerOrder> customerOrders;
        private List<Review> reviews;
        private Cart cart;

        public Builder setCustomerId(int customerId) { this.customerId = customerId; return this; }
        public Builder setFirstName(String firstName) { this.firstName = firstName; return this; }
        public Builder setLastName(String lastName) { this.lastName = lastName; return this; }
        public Builder setEmail(String email) { this.email = email; return this; }
        public Builder setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }
        public Builder password(String password) {
            this.password = password;
            return this;
        }
        public Builder setAddresses(List<Address> addresses) { this.addresses = addresses; return this; }
        public Builder setOrders(List<CustomerOrder> customerOrders) { this.customerOrders = customerOrders; return this; }
        public Builder setReviews(List<Review> reviews) { this.reviews = reviews; return this; }
        public Builder setCart(Cart cart) { this.cart = cart; return this; }

        public Builder copy(Customer customer) {
            this.customerId = customer.customerId;
            this.firstName = customer.firstName;
            this.lastName = customer.lastName;
            this.email = customer.email;
            this.phoneNumber = customer.phoneNumber;
            this.password = customer.password;
            this.addresses = customer.addresses;
            this.customerOrders = customer.customerOrders;
            this.reviews = customer.reviews;
            this.cart = customer.cart;
            return this;
        }

        public Customer build() { return new Customer(this); }
    }
}
