package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.domain.Product;
import za.ac.cput.service.ProductService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final String uploadDir = "uploads/";

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    // Create product with image
    @PostMapping(value = "/create", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public Product create(
            @RequestPart("product") Product product,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        if (file != null && !file.isEmpty()) {
            String filePath = uploadDir + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            product.getImagePath();
        }

        return productService.create(product);
    }

    // Read product
    @GetMapping("/read/{productId}")
    public Product read(@PathVariable(name = "productId") int productId) {
        return productService.read(productId);
    }

    // Update product
    @PutMapping("/update")
    public Product update(@RequestBody Product product) {
        return productService.update(product);
    }

    // Get all products
    @GetMapping("/getall")
    public List<Product> getAll(

    ) {
        return productService.getall();
    }

    // Fetch product image
    @GetMapping("/image/{productId}")
    public ResponseEntity<byte[]> getImage(@PathVariable int productId) throws IOException {
        Product product = productService.read(productId);

        if (product.getImagePath() == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] image = Files.readAllBytes(Paths.get(product.getImagePath()));
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }
}
