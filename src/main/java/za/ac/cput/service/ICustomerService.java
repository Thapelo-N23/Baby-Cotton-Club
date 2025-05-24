/**
 * BabyCottonClub
 * Product.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date : 24 May 2025
 */

package za.ac.cput.service;

import za.ac.cput.domain.Customer;

import java.util.List;

public interface ICustomerService extends IService<Customer, Integer> {
  List<Customer> getAll();
}
