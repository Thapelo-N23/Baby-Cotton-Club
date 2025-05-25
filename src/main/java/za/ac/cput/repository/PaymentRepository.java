/*
PaymentRepository POJO Class
Author: Phindile Lisa Ngozi
Student Number: 230640893
Date: 2025/05/25
 */
package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}

