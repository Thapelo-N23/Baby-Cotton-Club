/**
 * BabyCottonClub
 * Product.java
 * Author : Thapelo Ngwenya - 222260971
 * Date : 11 May 2025
 */


package za.ac.cput.service;

import za.ac.cput.domain.Product;

import java.util.List;

public interface ProductService extends IService<Product, Long> {
List<Product> getall();
}
