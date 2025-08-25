/**
 * BabyCottonClub
 * Product.java
 * Author : Thapelo Ngwenya - 222260971
 * Date : 11 May 2025
 */


package za.ac.cput.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Product;
import za.ac.cput.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
private ProductService productService;

@Autowired
    public ProductController(ProductService productService) {
    this.productService = productService;
}
@PostMapping("create")
    public Product create(@RequestBody Product product) {
    return productService.create(product);
}
@GetMapping("/read{productId}")
    public Product read(@PathVariable Integer productId) {
    return productService.read(productId);
}
    @PutMapping("/update")
    public Product update(@RequestBody Product product) {
    return productService.update(product);
    }
    @GetMapping("/getall")
    public List<Product> getAll() {
        return productService.getall();
    }

}
