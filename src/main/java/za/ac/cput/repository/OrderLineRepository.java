package za.ac.cput.repository;

import org.springframework.stereotype.Repository;
import za.ac.cput.domain.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, String> {
        String findOrderLineIdByProductId(String productId);

}
