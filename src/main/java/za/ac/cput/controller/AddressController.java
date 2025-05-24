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
import za.ac.cput.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
  private AddressService service;

  @Autowired
    public AddressController(AddressService addressService) {
        this.service = addressService;
    }

    @PostMapping("/create")
    public Address createAddress(@RequestBody Address address) {

        return service.create(address);
    }
    @GetMapping("/read/{id}")
    public Address readAddress(@PathVariable Integer id) {

        return service.read(id);
    }
    @PutMapping("/update")
    public Address updateAddress(@RequestBody Address address) {

        return service.update(address);
    }
    @GetMapping("/getall")
    public Iterable<Address> getAllAddresses() {

        return service.getAll();
    }


}
