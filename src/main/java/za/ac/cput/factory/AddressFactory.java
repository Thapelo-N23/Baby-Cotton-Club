
/**
 * BabyCottonClub
 * Product.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date : 18 May 2025
 */

package za.ac.cput.factory;

import za.ac.cput.domain.Address;
import za.ac.cput.util.Helper;

public class AddressFactory {

    public static Address createAddress(String streetName, short streetNumber, String suburb, String city, short postalCode, String province) {
        if (Helper.isNullOrEmpty(streetName) || Helper.isNullOrEmpty(suburb)
                || Helper.isNullOrEmpty(city) || Helper.isNullOrEmpty(province)) {
            return null;
        }

        if (!Helper.isValidPostalCode(postalCode)) {
            return null;
        }

        if (!Helper.isValidStreetNumber(streetNumber)) {
            return null;
        }


        return new Address.Builder()
                .setStreetName(streetName)
                .setStreetNumber(streetNumber)
                .setSuburb(suburb)
                .setCity(city)
                .setPostalCode(postalCode)
                .setProvince(province)
                .build();
    }
}

