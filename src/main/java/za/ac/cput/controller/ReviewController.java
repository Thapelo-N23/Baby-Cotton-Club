package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.Product;
import za.ac.cput.repository.ProductRepository;
import za.ac.cput.service.impl.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import za.ac.cput.domain.Customer;
import za.ac.cput.repository.CustomerRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewService service;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    public ReviewController(ReviewService reviewService){
        this.service = reviewService;
    }

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(Review review) {
        // Set reviewDate server-side
        review.setReviewDate(LocalDate.now());
        // Handle both Integer and int productId
        if (review.getProduct() != null) {
            Integer productId = review.getProduct().getProductId();
            if (productId != null && productId != 0) {
                Product product = productRepository.findById(productId).orElse(null);
                review.setProduct(product);
            }
        }
        // Optionally set customer if needed
        // review.setCustomer(...);
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