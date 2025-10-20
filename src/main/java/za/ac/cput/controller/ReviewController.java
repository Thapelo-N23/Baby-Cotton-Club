package za.ac.cput.controller;

import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.Product;
import za.ac.cput.repository.ProductRepository;
import za.ac.cput.repository.CustomerRepository;
import za.ac.cput.service.impl.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import za.ac.cput.domain.Customer;
import java.time.LocalDate;
import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService service;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public ReviewController(ReviewService service,
                            ProductRepository productRepository,
                            CustomerRepository customerRepository) {
        this.service = service;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        if (review == null) {
            return ResponseEntity.badRequest().build();
        }

        String authEmail = SecurityContextHolder.getContext().getAuthentication() != null
                ? SecurityContextHolder.getContext().getAuthentication().getName()
                : null;

        Integer customerId = null;
        if (review.getCustomer() != null) {
            customerId = review.getCustomer().getCustomerId();
        } else if (review.getCustomerId() != null) {
            customerId = review.getCustomerId();
        }

        if (customerId == null || customerId <= 0) {
            return ResponseEntity.badRequest().build();
        }

        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (authEmail == null || !authEmail.equals(customer.getEmail())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        review.setReviewDate(LocalDate.now());

        if (review.getProduct() != null) {
            int productId = review.getProduct().getProductId();
            if (productId > 0) {
                Product product = productRepository.findById(productId).orElse(null);
                review.setProduct(product);
            }
        } else if (review.getProductId() != null) {
            Product product = productRepository.findById(review.getProductId()).orElse(null);
            review.setProduct(product);
        }

        // attach managed customer entity
        if (review.getCustomer() != null && review.getCustomer().getCustomerId() > 0) {
            Customer c = customerRepository.findById(review.getCustomer().getCustomerId()).orElse(null);
            review.setCustomer(c);
        } else if (review.getCustomerId() != null) {
            Customer c = customerRepository.findById(review.getCustomerId()).orElse(null);
            review.setCustomer(c);
        }

        Review created = service.create(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Review> readReview(@PathVariable("id") Integer id){
        Review r = service.read(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Review> updateReview(@RequestBody Review review){
        Review updated = service.update(review);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Review>> getAllReviews(){
        return ResponseEntity.ok(service.getAll());
    }
}
