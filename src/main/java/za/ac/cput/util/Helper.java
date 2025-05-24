package za.ac.cput.util;

import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDate;

public class Helper {


    public static boolean isNullOrEmpty(String s) {

        return s == null || s.isEmpty();
    }


    public static LocalDate isValidDate(String Date) {
        int year = Integer.parseInt(Date.substring(0, 4));
        int month = Integer.parseInt(Date.substring(4, 6));
        int day = Integer.parseInt(Date.substring(6, 8));
        return LocalDate.of(year, month, day);
    }
    public static double calculateSubTotal(int quantity, double unitPrice) {
        return quantity * unitPrice;
    }
    public static boolean isValidEmail(String email){
        EmailValidator validator = EmailValidator.getInstance();
        if (validator.isValid(email)) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean isValidStreetNumber(short streetNumber) {
        if (streetNumber < 1 || streetNumber > 99999) {
            return false;
        }
        return true;
    }
    public static boolean isValidPostalCode(short postalCode) {
        if (postalCode < 1000 || postalCode > 9999) {
            return false;
        }
        return true;

    }

    public static boolean isValidCategoryId(Integer categoryId) {
        return categoryId != null && categoryId > 0;
    }

    public static boolean isValidReviewId(Integer reviewId) {
        return reviewId != null && reviewId > 0;
    }

    public static boolean isValidId(int inventoryId) {
        return false;
    }
}

