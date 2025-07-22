/**
 * BabyCottonClub
 * Product.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date : 18 May 2025
 */

package za.ac.cput.factory;

import za.ac.cput.domain.Address;
import za.ac.cput.domain.Customer;
import za.ac.cput.util.Helper;

import java.util.Arrays;

public class CustomerFactory {

    public static Customer createCustomer(String firstName, String lastName, String email, String phoneNumber,
                                          String streetName, short streetNumber, String suburb, String city,
                                          short postalCode, String province) {

        if (Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName) || Helper.isNullOrEmpty(phoneNumber)) {
            return null;
        }

        if (!Helper.isValidEmail(email)) {
            return null;
        }

        Address address = AddressFactory.createAddress(streetName, streetNumber, suburb, city, postalCode, province);
        if (address == null) {
            return null;
        }


        return new Customer.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setAddresses(Arrays.asList(address))
                .build();
    }
}
