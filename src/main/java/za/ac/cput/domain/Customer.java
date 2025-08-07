/**
 * BabyCottonClub
 * Product.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date : 11 May 2025
 */

package za.ac.cput.domain;

import jakarta.persistence.*;
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

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Address> addresses;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Review> reviews;

    public Customer() {}

    public Customer(Builder builder) {
        this.customerId = builder.customerId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.addresses = builder.addresses;
        this.orders = builder.orders;
        this.reviews = builder.reviews;
    }

    public int getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public List<Address> getAddresses() { return addresses; }
    public List<Order> getOrders() { return orders; }
    public List<Review> getReviews() { return reviews; }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", addressCount=" + (addresses != null ? addresses.size() : 0) +
                ", orderCount=" + (orders != null ? orders.size() : 0) +
                ", reviewCount=" + (reviews != null ? reviews.size() : 0) +
                '}';
    }


    public static class Builder {
        private int customerId;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private List<Address> addresses;
        private List<Order> orders;
        private List<Review> reviews;

        public Builder setCustomerId(int customerId) { this.customerId = customerId; return this; }
        public Builder setFirstName(String firstName) { this.firstName = firstName; return this; }
        public Builder setLastName(String lastName) { this.lastName = lastName; return this; }
        public Builder setEmail(String email) { this.email = email; return this; }
        public Builder setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }
        public Builder setAddresses(List<Address> addresses) { this.addresses = addresses; return this; }
        public Builder setOrders(List<Order> orders) { this.orders = orders; return this; }
        public Builder setReviews(List<Review> reviews) { this.reviews = reviews; return this; }

        public Builder copy(Customer customer) {
            this.customerId = customer.customerId;
            this.firstName = customer.firstName;
            this.lastName = customer.lastName;
            this.email = customer.email;
            this.phoneNumber = customer.phoneNumber;
            this.addresses = customer.addresses;
            this.orders = customer.orders;
            this.reviews = customer.reviews;
            return this;
        }

        public Customer build() { return new Customer(this); }
    }
}
