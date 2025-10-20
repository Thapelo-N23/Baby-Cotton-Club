/**
 * BabyCottonClub
 * Product.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date : 24 May 2025
 */

package za.ac.cput.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Address;
import za.ac.cput.service.impl.AddressService;
import za.ac.cput.repository.CustomerRepository;
import za.ac.cput.domain.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/address")
public class AddressController {
  private AddressService service;

  @Autowired
    private CustomerRepository customerRepository;

  @Autowired
    public AddressController(AddressService addressService) {
        this.service = addressService;
    }

    @PostMapping("/create")
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (auth != null) ? auth.getName() : null;
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Customer customer = customerRepository.findByEmail(username);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // ensure the saved address is associated with the authenticated customer
        Address toSave = new Address.Builder().copy(address).setCustomer(customer).build();
        Address saved = service.create(toSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    @GetMapping("/read/{id}")
    public ResponseEntity<Address> readAddress(@PathVariable("id") Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (auth != null) ? auth.getName() : null;
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Address found = service.read(id);
        if (found == null) return ResponseEntity.notFound().build();
        if (found.getCustomer() == null || !username.equals(found.getCustomer().getEmail())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(found);
    }
    @PutMapping("/update")
    public ResponseEntity<Address> updateAddress(@RequestBody Address address) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (auth != null) ? auth.getName() : null;
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Address existing = service.read(address.getAddressId());
        if (existing == null) return ResponseEntity.notFound().build();
        if (existing.getCustomer() == null || !username.equals(existing.getCustomer().getEmail())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        // preserve ownership
        Address toUpdate = new Address.Builder().copy(address).setCustomer(existing.getCustomer()).build();
        Address updated = service.update(toUpdate);
        return ResponseEntity.ok(updated);
    }
    @GetMapping("/getall")
    public ResponseEntity<List<Address>> getAllAddresses() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (auth != null) ? auth.getName() : null;
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<Address> addresses = service.getAll().stream()
                .filter(a -> a.getCustomer() != null && username.equals(a.getCustomer().getEmail()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(addresses);
    }


}
