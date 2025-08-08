/*
 * BabyCottonClub
 * ShipmentRepository.java
 * Author : Thapelo Ngwenya - 222260971
 * Date : 11 May 2025
 */

package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {
}
