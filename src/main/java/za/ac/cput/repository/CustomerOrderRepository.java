/*
Baby Cotton Club
OrderRespository
Author: Tsireledzo Netshilonwe
Student Number: 230666426
Date: 2025/05/24
*/

package za.ac.cput.repository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer>{
    @Query("SELECT o FROM CustomerOrder o WHERE o.customer.id = :customerId")
    List<CustomerOrder> findByCustomer_Id(@Param("customerId") Integer customerId);
}
