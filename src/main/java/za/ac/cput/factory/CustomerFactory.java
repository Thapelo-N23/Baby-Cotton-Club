/**
 * BabyCottonClub
 * Product.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date : 18 May 2025
 */

package za.ac.cput.factory;

import za.ac.cput.domain.*;
import za.ac.cput.util.Helper;

import java.util.List;

public class CustomerFactory {

    public static Customer createCustomer(String firstName, String lastName, String email, String phoneNumber,
                                          String password,
                                          List<Address> addresses, List<CustomerOrder> customerOrders, List<Review> reviews, Cart cart) {

        if (Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName) || Helper.isNullOrEmpty(phoneNumber) || Helper.isNullOrEmpty(password)) {
            return null;
        }

        if (!Helper.isValidEmail(email)) {
            return null;

        }
            return new Customer.Builder()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setEmail(email)
                    .setPhoneNumber(phoneNumber)
                    .password(password)
                    .setAddresses(addresses)
                    .setOrders(customerOrders)
                    .setReviews(reviews)
                    .setCarts(cart == null ? null : List.of(cart))
                    .build();
        }

    }
