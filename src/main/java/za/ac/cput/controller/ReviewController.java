package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.Product;
import za.ac.cput.repository.ProductRepository;
import za.ac.cput.repository.CustomerRepository;
import za.ac.cput.service.impl.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewService service;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    public ReviewController(ReviewService reviewService){
        this.service = reviewService;
    }

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        if (review == null) {
            return ResponseEntity.badRequest().build();
        }


        review.setReviewDate(LocalDate.now());

        if (review.getProduct() != null) {
            Integer productId = review.getProduct().getProductId();
            if (productId != null && productId != 0) {
                Product product = productRepository.findById(productId).orElse(null);
                review.setProduct(product);
            }
        } else if (review.getProductId() != null) {
            Product product = productRepository.findById(review.getProductId()).orElse(null);
            review.setProduct(product);
        }


        if (review.getCustomer() != null) {
            Integer customerId = review.getCustomer().getCustomerId();

            if (customerId != null && customerId != 0) {
                var customer = customerRepository.findById(customerId).orElse(null);
                review.setCustomer(customer);
            }
        } else if (review.getCustomerId() != null) {
            var customer = customerRepository.findById(review.getCustomerId()).orElse(null);
            review.setCustomer(customer);
        }


        Review created = service.create(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @GetMapping("/read/{id}")
    public Review readReview(@PathVariable ("id") Integer id){
        return service.read(id);
    }

    @PutMapping("/update")
    public Review updateReview(@RequestBody Review review){
        return service.update(review);
    }

    @GetMapping("/getall")
    public List<Review> getAllReviews(){
        return service.getAll();
    }
}