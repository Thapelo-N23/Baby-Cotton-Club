package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Customer;
import za.ac.cput.repository.ProductRepository;
import za.ac.cput.repository.CustomerRepository;
import za.ac.cput.service.impl.ReviewService;
import za.ac.cput.dto.CreateReviewRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Review> createReview(@Valid @RequestBody CreateReviewRequest req) {
        Integer productId = req.getProductId();
        Short rating = req.getRating();
        String reviewComment = req.getReviewComment();
        // Set reviewDate server-side
        LocalDate reviewDate = LocalDate.now();
        // For now, still get customerId from request (should be from authentication in production)
        // Integer customerId = ...
        Product product = productRepository.findById(productId).orElse(null);
        // Customer customer = ...
        Review review = new Review.Builder()
                .setProduct(product)
                // .setCustomer(customer)
                .setRating(rating)
                .setReviewComment(reviewComment)
                .setReviewDate(reviewDate)
                .build();
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