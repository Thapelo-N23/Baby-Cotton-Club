/*
Baby Cotton Club
OrderRespository
Author: Tsireledzo Netshilonwe
Student Number: 230666426
Date: 2025/05/24
*/

package za.ac.cput.repository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrderRespository extends JpaRepository<Order, String>{
    String findOrderIdByCustomerId(String customerId);
}
