
/*
IPaymentService POJO Class
Author: Phindile Lisa Ngozi
Student Number: 230640893
Date: 2025/05/25
 */
package za.ac.cput.service;

import za.ac.cput.domain.Payment;
import java.util.List;

public interface IPaymentService extends IService<Payment, String> {
    boolean delete(String id);

    List<Payment> getAll();
}

