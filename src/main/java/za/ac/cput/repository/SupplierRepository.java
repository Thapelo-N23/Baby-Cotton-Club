
/*
SupplierRepository POJO Class
Author: Phindile Lisa Ngozi
Student Number: 230640893
Date: 2025/05/25
 */
package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}

