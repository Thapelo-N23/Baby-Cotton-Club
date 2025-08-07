/*
 * BabyCottonClub
 * Product.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date : 11 May 2025
 */

package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int addressId;

    protected short streetNumber;
    protected String streetName;
    protected String suburb;
    protected String city;
    protected short postalCode;
    protected String province;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    protected Address() {}

    private Address(Builder builder) {
        this.addressId = builder.addressId;
        this.streetNumber = builder.streetNumber;
        this.streetName = builder.streetName;
        this.suburb = builder.suburb;
        this.city = builder.city;
        this.postalCode = builder.postalCode;
        this.province = builder.province;
        this.customer = builder.customer;
    }

    public int getAddressId() { return addressId; }
    public short getStreetNumber() { return streetNumber; }
    public String getStreetName() { return streetName; }
    public String getSuburb() { return suburb; }
    public String getCity() { return city; }
    public short getPostalCode() { return postalCode; }
    public String getProvince() { return province; }
    public Customer getCustomer() { return customer; }


    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", streetNumber=" + streetNumber +
                ", streetName='" + streetName + '\'' +
                ", suburb='" + suburb + '\'' +
                ", city='" + city + '\'' +
                ", postalCode=" + postalCode +
                ", province='" + province + '\'' +
                ", customerId=" + (customer != null ? customer.getCustomerId() : "null") +
                '}';
    }


    public static class Builder {
        private int addressId;
        private short streetNumber;
        private String streetName;
        private String suburb;
        private String city;
        private short postalCode;
        private String province;
        private Customer customer;

        public Builder setAddressId(int addressId) { this.addressId = addressId; return this; }
        public Builder setStreetNumber(short streetNumber) { this.streetNumber = streetNumber; return this; }
        public Builder setStreetName(String streetName) { this.streetName = streetName; return this; }
        public Builder setSuburb(String suburb) { this.suburb = suburb; return this; }
        public Builder setCity(String city) { this.city = city; return this; }
        public Builder setPostalCode(short postalCode) { this.postalCode = postalCode; return this; }
        public Builder setProvince(String province) { this.province = province; return this; }
        public Builder setCustomer(Customer customer) { this.customer = customer; return this; }

        public Builder copy(Address address) {
            this.addressId = address.addressId;
            this.streetNumber = address.streetNumber;
            this.streetName = address.streetName;
            this.suburb = address.suburb;
            this.city = address.city;
            this.postalCode = address.postalCode;
            this.province = address.province;
            this.customer = address.customer;
            return this;
        }

        public Address build() { return new Address(this); }
    }
}
