/**
 * BabyCottonClub
 * Shipment.java
 * Author : Thapelo Ngwenya - 222260971
 * Date : 11 May 2025
 */


package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Shipment;

import java.util.List;
import java.util.Optional;


//@Repository
//    public interface CustomerRepository extends JpaRepository<Customer,String> {
//
//        Optional<Customer> findByEmail(String email);
//        Optional<Customer> findByPhone(String phone);
//        List<Customer> findAll();
//
//    }
@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, String > {
    Optional<Shipment> findByShipmentId(String id);


}
