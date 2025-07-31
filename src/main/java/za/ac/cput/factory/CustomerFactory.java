/**
 * BabyCottonClub
 * Product.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date : 18 May 2025
 */

package za.ac.cput.factory;

import za.ac.cput.domain.Address;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.Review;
import za.ac.cput.util.Helper;

import java.util.Arrays;
import java.util.List;

public class CustomerFactory {

    public static Customer createCustomer(String firstName, String lastName, String email, String phoneNumber,
                                          List<Address> addresses, List<Order> orders, List<Review> reviews) {

        if (Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName) || Helper.isNullOrEmpty(phoneNumber)) {
            return null;
        }

        if (!Helper.isValidEmail(email)) {
            return null;
        }
        if (addresses == null) {
            return null;
        }
        if (orders == null ) {
            return null;
        }
        if (reviews == null) {
            return null;
        }

        return new Customer.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setAddresses(addresses)
                .setOrders(orders)
                .setReviews(reviews)
                .build();
    }

}


