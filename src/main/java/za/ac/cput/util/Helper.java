package za.ac.cput.util;

import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDate;

public class Helper {


    public static boolean isNullOrEmpty(String s) {

        return s == null || s.isEmpty();
    }


    public static LocalDate isValidDate(String date) {
        // Support both "yyyy-MM-dd" and "yyyyMMdd" formats
        if (date == null) throw new IllegalArgumentException("Date string is null");
        if (date.contains("-")) {
            return LocalDate.parse(date); // ISO format
        } else if (date.length() == 8) {
            int year = Integer.parseInt(date.substring(0, 4));
            int month = Integer.parseInt(date.substring(4, 6));
            int day = Integer.parseInt(date.substring(6, 8));
            return LocalDate.of(year, month, day);
        } else {
            throw new IllegalArgumentException("Invalid date format: " + date);
        }
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

    public static boolean isValidAmount(double amount) {
        return amount >= 0.0;
    }
    public static boolean isNullOrEmpty(int supplierId) {
        return false;
    }

    public static boolean isValidProductId(Long productId) {
        return productId != null && productId > 0;
    }

    public static boolean isValidPrice(Short price) {
        return price != null && price > 0;
    }

    public static boolean isValidShipmentId(int id) {
        return id > 0;
    }



}
