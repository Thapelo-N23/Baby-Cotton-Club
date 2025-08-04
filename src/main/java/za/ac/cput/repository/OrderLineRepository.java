
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


@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
        Integer findOrderLineIdByProductId(Integer productId);

}
