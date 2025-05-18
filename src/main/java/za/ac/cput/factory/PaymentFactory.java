/*
PaymentFactory POJO Class
Author: Phindile Lisa Ngozi
Student Number: 230640893
Date: 2025/05/18
 */
package za.ac.cput.factory;

import za.ac.cput.domain.Payment;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class PaymentFactory {

    public static Payment createPayment(String paymentId, String orderId, String paymentDate,
                                        String paymentMethod, String paymentStatus, double amount) {

        if (Helper.isNullOrEmpty(paymentId) || Helper.isNullOrEmpty(orderId) ||
                Helper.isNullOrEmpty(paymentMethod) || Helper.isNullOrEmpty(paymentStatus)
                || !Helper.isValidAmount(amount)) {

            return null;
        }

            LocalDate date = Helper.isValidDate(paymentDate);
        if (date == null) {
            return null;
        }


            return new Payment.Builder()
                    .setPaymentId(paymentId)
                    .setOrderId(orderId)
                    .setPaymentDate(date)
                    .setPaymentMethod(paymentMethod)
                    .setPaymentStatus(paymentStatus)
                    .setAmount(amount)
                    .build();

        }


    }
