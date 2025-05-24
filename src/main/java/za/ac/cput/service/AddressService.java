/**
 * BabyCottonClub
 * Product.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date : 24 May 2025
 */


package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Address;
import za.ac.cput.repository.AddressRepository;

import java.util.List;

@Service
public class AddressService implements IAddressService {

    private AddressRepository addressRepository;
    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    @Override
    public Address create(Address address) {
        return this.addressRepository.save(address);
    }

    @Override
    public Address read(Integer s) {
        return this.addressRepository.findById(s).orElse(null);
    }

    @Override
    public Address update(Address address) {
        return this.addressRepository.save(address);
    }

    @Override
    public List<Address> getAll() {
        return this.addressRepository.findAll();
    }

}
