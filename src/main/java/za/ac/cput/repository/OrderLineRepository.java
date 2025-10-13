/*
Baby Cotton Club
OrderLineRepository
Author: Tsireledzo Netshilonwe
Student Number: 230666426
Date: 2025/05/24
*/
package za.ac.cput.repository;

import org.springframework.stereotype.Repository;
import za.ac.cput.domain.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Customer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {

    @Query("SELECT ol FROM OrderLine ol WHERE ol.product = :product AND ol.customerOrder.customer = :customer")
    List<OrderLine> findByProductAndCustomer(@Param("product") Product product, @Param("customer") Customer customer);
}
