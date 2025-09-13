package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Product;
import za.ac.cput.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Create product
    @PostMapping("/create")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.ok(productService.create(product));
    }

    // Get single product by ID
    @GetMapping("/read/{productId}")
    public ResponseEntity<Product> read(@PathVariable int productId) {
        Product product = productService.read(productId);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    // Update product
    @PutMapping("/update/{productId}")
    public ResponseEntity<Product> update(
            @PathVariable int productId,
            @RequestBody Product product) {
        product.setProductId(productId);
        Product updated = productService.update(product);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // Get all products
    @GetMapping("/getall")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getall());
    }
}
