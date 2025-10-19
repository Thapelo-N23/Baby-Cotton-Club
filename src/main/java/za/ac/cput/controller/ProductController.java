package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Category;
import za.ac.cput.domain.Supplier;
import za.ac.cput.service.ProductService;
import za.ac.cput.repository.CategoryRepository;
import za.ac.cput.repository.SupplierRepository;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Create product
    @PostMapping("/create")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        // Ensure category is managed/persistent
        Category cat = product.getCategory();
        if (cat != null) {
            try {
                if (cat.getCategoryId() != 0) {
                    Category existing = categoryRepository.findById(cat.getCategoryId()).orElse(null);
                    product.setCategory(existing);
                } else if (cat.getCategoryName() != null && !cat.getCategoryName().isEmpty()) {
                    Category existingByName = categoryRepository.findByCategoryName(cat.getCategoryName());
                    if (existingByName != null) {
                        product.setCategory(existingByName);
                    } else {
                        Category newCat = new Category.Builder().setCategoryName(cat.getCategoryName()).build();
                        Category saved = categoryRepository.save(newCat);
                        product.setCategory(saved);
                    }
                }
            } catch (Exception e) {
                // if anything goes wrong, clear category to avoid transient save failures
                product.setCategory(null);
            }
        }

        // Ensure supplier is managed if id provided
        Supplier sup = product.getSupplier();
        if (sup != null) {
            if (sup.getSupplierId() != 0) {
                Supplier existingSup = supplierRepository.findById(sup.getSupplierId()).orElse(null);
                product.setSupplier(existingSup);
            }
        }

        return ResponseEntity.ok(productService.create(product));
    }

    // Get single product by ID
    @GetMapping("/read/{productId}")
    public ResponseEntity<Product> read(@PathVariable("productId") int productId) {
        Product product = productService.read(productId);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    // Update product
    @PutMapping("/update/{productId}")
    public ResponseEntity<Product> update(
            @PathVariable("productId") int productId,
            @RequestBody Product product) {
        product.setProductId(productId);
        Product updated = productService.update(product);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") int id) {
        boolean deleted = productService.delete(id);
        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    // Get all products
    @GetMapping("/getall")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getall());
    }
}
