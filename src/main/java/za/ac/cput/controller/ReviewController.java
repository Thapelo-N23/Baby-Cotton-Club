package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Customer;
import za.ac.cput.repository.ProductRepository;
import za.ac.cput.repository.CustomerRepository;
import za.ac.cput.service.impl.ReviewService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/review")
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
    public Review createReview(@RequestBody Map<String, Object> payload){
        Integer productId = (Integer) payload.get("productId");
        Integer customerId = (Integer) payload.get("customerId");
        Short rating = ((Number) payload.get("rating")).shortValue();
        String reviewComment = (String) payload.get("reviewComment");
        LocalDate reviewDate = LocalDate.parse((String) payload.get("reviewDate"));

        Product product = productRepository.findById(productId).orElse(null);
        Customer customer = customerRepository.findById(customerId).orElse(null);

        Review review = new Review.Builder()
                .setProduct(product)
                .setCustomer(customer)
                .setRating(rating)
                .setReviewComment(reviewComment)
                .setReviewDate(reviewDate)
                .build();

        return service.create(review);
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